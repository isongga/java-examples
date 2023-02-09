package com.rj.demo.blockchain.diy.block;

import com.rj.demo.blockchain.diy.transaction.Transaction;
import com.rj.demo.blockchain.diy.util.ByteUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Block {
    private String hash;
    private String prevBlockHash;
    private Transaction[] transactions;
    /**
     * 工作量证明计数器
     */
    private long nonce;
    private long timestamp;

    public static Block newBlock(String previousHash, Transaction[] transactions) {
        Block block = new Block("", previousHash, transactions,  0,Instant.now().getEpochSecond());
        ProofOfWork pow = ProofOfWork.newProofOfWork(block);
        PowResult powResult = pow.run();
        block.setHash(powResult.getHash());
        block.setNonce(powResult.getNonce());
        return block;
    }

//    private void setHash() {
//        byte[] prevBlockHashBytes = {};
//        if (StringUtils.isNoneBlank(this.getPrevBlockHash())) {
//            prevBlockHashBytes = new BigInteger(this.getPrevBlockHash(), 16).toByteArray();
//        }
//
//        byte[] headers = ByteUtils.merge(
//                prevBlockHashBytes,
//                this.getData().getBytes(),
//                ByteUtils.toBytes(this.getTimestamp()));
//
//        this.setHash(DigestUtils.sha256Hex(headers));
//    }

    public static Block newGenesisBlock(Transaction coinbase) {
        return Block.newBlock("", new Transaction[]{coinbase});
    }

    public byte[] hashTransactions() {
        byte[][] txIdArrays = new byte[this.getTransactions().length][];
        for (int i = 0; i < this.getTransactions().length; i++) {
            txIdArrays[i] = this.getTransactions()[i].getTxId();
        }
        return DigestUtils.sha256(ByteUtils.merge(txIdArrays));
    }

}
