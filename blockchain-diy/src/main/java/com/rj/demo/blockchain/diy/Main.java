package com.rj.demo.blockchain.diy;

import com.rj.demo.blockchain.diy.CLI;

public class Main {

    public static void main(String[] args) {

        CLI cli = new CLI(args);
        cli.parse();

//        try {
//            Blockchain blockchain = Blockchain.newBlockchain();
////            blockchain.addBlock("Send 1.0 BTC to wangwei");
////            blockchain.addBlock("Send 2.5 more BTC to wangwei");
////            blockchain.addBlock("Send 3.5 more BTC to wangwei");
//
//            for (Blockchain.BlockchainIterator iterator = blockchain.getBlockchainIterator(); iterator.hashNext(); ) {
//                Block block = iterator.next();
//
//                if (block != null) {
//                    boolean validate = ProofOfWork.newProofOfWork(block).validate();
//                    System.out.println(block.toString() + ", validate = " + validate);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

}