package com.rj.demo.blockchain.diy.block;

import com.rj.demo.blockchain.diy.transaction.SpendableOutputResult;
import com.rj.demo.blockchain.diy.transaction.TXInput;
import com.rj.demo.blockchain.diy.transaction.TXOutput;
import com.rj.demo.blockchain.diy.transaction.Transaction;
import com.rj.demo.blockchain.diy.util.RocksDBUtils;
import lombok.Getter;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Blockchain {
    @Getter
    private String lastBlockHash;

    public Blockchain(String lastBlockHash) {
        this.lastBlockHash = lastBlockHash;
    }

    public void addBlock(Transaction[] data) throws Exception{
        String lastBlockHash = RocksDBUtils.getInstance().getLastBlockHash();
        if (StringUtils.isBlank(lastBlockHash)) {
            throw new Exception("Fail to add block into blockchain ! ");
        }
        this.addBlock(Block.newBlock(lastBlockHash, data));
    }

    public void addBlock(Block block) {
        RocksDBUtils.getInstance().putLastBlockHash(block.getHash());
        RocksDBUtils.getInstance().putBlock(block);
        this.lastBlockHash = block.getHash();
    }

    /**
     * 打包交易，进行挖矿
     *
     * @param transactions
     */
    public void mineBlock(Transaction[] transactions) throws Exception {
        String lastBlockHash = RocksDBUtils.getInstance().getLastBlockHash();
        if (lastBlockHash == null) {
            throw new Exception("ERROR: Fail to get last block hash ! ");
        }
        Block block = Block.newBlock(lastBlockHash, transactions);
        this.addBlock(block);
    }

    public static Blockchain createBlockchain(String address) {
        String lastBlockHash = RocksDBUtils.getInstance().getLastBlockHash();
        if (StringUtils.isBlank(lastBlockHash)) {
            Transaction coinbase = Transaction.newCoinbaseTX(address,"");
            Block genesisBlock = Block.newGenesisBlock(coinbase);
            lastBlockHash = genesisBlock.getHash();
            RocksDBUtils.getInstance().putBlock(genesisBlock);
            RocksDBUtils.getInstance().putLastBlockHash(lastBlockHash);
        }
        return new Blockchain(lastBlockHash);
    }

    public BlockchainIterator getBlockchainIterator() {
        return new BlockchainIterator(lastBlockHash);
    }

    public class BlockchainIterator {
        private String currentBlockHash;
        public BlockchainIterator(String currentBlockHash) {
            this.currentBlockHash = currentBlockHash;
        }

        /**
         * 是否有下一个区块
         *
         * @return
         */
        public boolean hasNext() throws Exception {
            if (StringUtils.isBlank(currentBlockHash)) {
                return false;
            }
            Block lastBlock = RocksDBUtils.getInstance().getBlock(currentBlockHash);
            if (lastBlock == null) {
                return false;
            }
            // 创世区块直接放行
            if (lastBlock.getPrevBlockHash().length() == 0) {
                return true;
            }
            return RocksDBUtils.getInstance().getBlock(lastBlock.getPrevBlockHash()) != null;
        }


        /**
         * 返回区块
         *
         * @return
         */
        public Block next() throws Exception {
            Block currentBlock = RocksDBUtils.getInstance().getBlock(currentBlockHash);
            if (currentBlock != null) {
                this.currentBlockHash = currentBlock.getPrevBlockHash();
                return currentBlock;
            }
            return null;
        }
    }

    /**
     * 查找钱包地址对应的所有未花费的交易
     * 确切地说是，未被完全花费的交易，可能花费了一部分，也可能根本没有花费过。
     * @param address 钱包地址
     * @return
     */
    private Transaction[] findUnspentTransactions(String address) throws Exception {
        Map<String, int[]> allSpentTXOs = this.findAllSpentOutputs(address);
        Transaction[] unspentTxs = {};

        // 再次遍历所有区块中的交易输出
        for (BlockchainIterator blockchainIterator = this.getBlockchainIterator(); blockchainIterator.hasNext(); ) {
            Block block = blockchainIterator.next();
            for (Transaction transaction : block.getTransactions()) {

                String txId = Hex.encodeHexString(transaction.getTxId());

                int[] spentOutIndexArray = allSpentTXOs.get(txId);

                for (int outIndex = 0; outIndex < transaction.getOutputs().length; outIndex++) {
                    if (spentOutIndexArray != null && ArrayUtils.contains(spentOutIndexArray, outIndex)) {
                        continue;
                    }

                    // 保存不存在 allSpentTXOs 中的交易
                    if (transaction.getOutputs()[outIndex].canBeUnlockedWith(address)) {
                        unspentTxs = ArrayUtils.add(unspentTxs, transaction);
                    }
                }
            }
        }
        return unspentTxs;
    }


    /**
     * 从交易输入中查询区块链中所有已被花费了的交易输出
     *
     * @param address 钱包地址
     * @return 交易ID以及对应的交易输出下标地址
     * @throws Exception
     */
     public Map<String, int[]> findAllSpentOutputs(String address) throws Exception {
        // 定义TxId ——> spentOutIndex[]，存储交易ID与已被花费的交易输出数组索引值
        Map<String, int[]> spentTXOs = new HashMap<>();
        for (BlockchainIterator blockchainIterator = this.getBlockchainIterator(); blockchainIterator.hasNext(); ) {
            Block block = blockchainIterator.next();

            for (Transaction transaction : block.getTransactions()) {
                // 如果是 coinbase 交易，直接跳过，因为它不存在引用前一个区块的交易输出
                if (transaction.isCoinbase()) {
                    continue;
                }
                for (TXInput txInput : transaction.getInputs()) {
                    if (txInput.canUnlockOutputWith(address)) {
                        String inTxId = Hex.encodeHexString(txInput.getTxId());
                        int[] spentOutIndexArray = spentTXOs.get(inTxId);
                        if (spentOutIndexArray == null) {
                            spentTXOs.put(inTxId, new int[]{txInput.getTxOutputIndex()});
                        } else {
                            spentOutIndexArray = ArrayUtils.add(spentOutIndexArray, txInput.getTxOutputIndex());
                            spentTXOs.put(inTxId, spentOutIndexArray);
                        }
                    }
                }
            }
        }
        return spentTXOs;
    }

    /**
     * 查找钱包地址对应的所有UTXO
     * UTXO 包含在UTX中 （确切地说是，未被完全花费的交易，可能花费了一部分，也可能根本没有花费过。）
     * @param address 钱包地址
     * @return
     */
    public TXOutput[] findUTXO(String address) throws Exception {
        Transaction[] unspentTxs = this.findUnspentTransactions(address);
        TXOutput[] utxos = {};
        if (unspentTxs == null || unspentTxs.length == 0) {
            return utxos;
        }
        for (Transaction tx : unspentTxs) {
            for (TXOutput txOutput : tx.getOutputs()) {
                if (txOutput.canBeUnlockedWith(address)) {
                    utxos = ArrayUtils.add(utxos, txOutput);
                }
            }
        }
        return utxos;
    }


    /**
     * 寻找能够花费的交易
     *
     * @param address 钱包地址
     * @param amount  花费金额
     */
    public SpendableOutputResult findSpendableOutputs(String address, int amount) throws Exception {
        Transaction[] unspentTXs = this.findUnspentTransactions(address);
        int accumulated = 0;
        Map<String, int[]> unspentOuts = new HashMap<>();
        for (Transaction tx : unspentTXs) {

            String txId = Hex.encodeHexString(tx.getTxId());

            for (int outId = 0; outId < tx.getOutputs().length; outId++) {

                TXOutput txOutput = tx.getOutputs()[outId];

                if (txOutput.canBeUnlockedWith(address) && accumulated < amount) {
                    accumulated += txOutput.getValue();

                    int[] outIds = unspentOuts.get(txId);
                    if (outIds == null) {
                        outIds = new int[]{outId};
                    } else {
                        outIds = ArrayUtils.add(outIds, outId);
                    }
                    unspentOuts.put(txId, outIds);
                    if (accumulated >= amount) {
                        break;
                    }
                }
            }
        }
        return new SpendableOutputResult(accumulated, unspentOuts);
    }
}
