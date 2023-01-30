package com.rj.demo.blockchain.diy.block;

import com.rj.demo.blockchain.diy.util.ByteUtils;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;

@Data
public class ProofOfWork {
    //设定一个难度目标位TARGET_BITS，表示最终挖矿挖出来Hash值，转化为二进制后，与256相比，长度少了多少bit，也即二进制前面有多少bit是零.
    //这里的TARGET_BITS是固定的，但是在真实的比特币中，难度目标是随着时间的推推，会动态调整的.
    public static final int TARGET_BITS = 20;
    //TARGET_BITS 越大，最终target就越小，要求计算出来的Hash越来越小，也就是挖矿的难度越来越大。

    private BigInteger target;
    //
    private Block block;

    private ProofOfWork(Block block, BigInteger target) {
        this.block = block;
        this.target = target;
    }

    public static ProofOfWork newProofOfWork(Block block) {
        BigInteger targetValue = BigInteger.valueOf(1).shiftLeft((256 - TARGET_BITS));
        return new ProofOfWork(block, targetValue);
    }

    public PowResult run() {
        long nonce = 0;
        String shaHex = "";
        System.out.printf("Mining the block containing：%d \n", this.getBlock().getTransactions().length);

        long startTime = System.currentTimeMillis();
        while (nonce < Long.MAX_VALUE) {
            byte[] data = this.prepareData(nonce);
            shaHex = DigestUtils.sha256Hex(data);
            //16进制
            if (new BigInteger(shaHex, 16).compareTo(this.target) == -1) {
                System.out.printf("Elapsed Time: %s seconds \n", (float) (System.currentTimeMillis() - startTime) / 1000);
                System.out.printf("correct hash Hex: %s \n\n", shaHex);
                break;
            } else {
                nonce++;
            }
        }
        return new PowResult(nonce, shaHex);
    }

    private byte[] prepareData(long nonce) {
        byte[] prevBlockHashBytes = {};
        if (StringUtils.isNoneBlank(this.getBlock().getPrevBlockHash())) {
            prevBlockHashBytes = new BigInteger(this.getBlock().getPrevBlockHash(), 16).toByteArray();
        }

        return ByteUtils.merge(
                prevBlockHashBytes,
                this.getBlock().hashTransaction(),
                ByteUtils.toBytes(this.getBlock().getTimestamp()),
                ByteUtils.toBytes(TARGET_BITS),
                ByteUtils.toBytes(nonce)
        );
    }

    public boolean validate() {
        byte[] data = this.prepareData(this.getBlock().getNonce());
        return new BigInteger(DigestUtils.sha256Hex(data), 16).compareTo(this.target) == -1;
    }
}
