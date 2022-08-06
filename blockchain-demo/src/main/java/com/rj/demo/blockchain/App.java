package com.rj.demo.blockchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.websocket.WebSocketClient;
import org.web3j.protocol.websocket.WebSocketService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigInteger;
import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@EnableScheduling
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }

    @PostConstruct
    public void listen() throws IOException, URISyntaxException, ExecutionException, InterruptedException {
        WebSocketService ws = new WebSocketService("wss://speedy-nodes-nyc.moralis.io/118d2691685ecc040712d0a1/eth/mainnet/ws", true);
        ws.connect();
        Web3j web3j = Web3j.build(ws);

        Map<String, Transaction> txs = new HashMap<>();

        web3j.pendingTransactionFlowable().subscribe(tx -> {
            System.out.println("pendting txs size: " + txs.size());
            System.out.println("pending tx ==> " + tx.getHash() + " === " +tx.getBlockNumberRaw());
            txs.put(tx.getHash(), tx);
        });




//        final WebSocketClient webSocketClient = new WebSocketClient(new URI("wss://matic.getblock.io/mainnet/?api_key=c42afaa0-7d61-4253-ac6d-84212cbbadb6"));
//        final boolean includeRawResponses = false;
//        final WebSocketService webSocketService = new WebSocketService(webSocketClient, includeRawResponses);
//
//        System.out.println("====");
//
////         Request to get a version of an Ethereum client
//        final Request<?, Web3ClientVersion> request = new Request<>(
//                // Name of an RPC method to call
//                "web3_clientVersion",
//                // Parameters for the method. "web3_clientVersion" does not expect any
//                Collections.<String>emptyList(),
//                // Service that is used to send a request
//                webSocketService,
//                // Type of an RPC call to get an Ethereum client version
//                Web3ClientVersion.class);
////
////// Send an asynchronous request via WebSocket protocol
////        // Send a (synchronous) request via WebSocket protocol
//        final Web3ClientVersion clientVersion = webSocketService.send(
//                request,
//                Web3ClientVersion.class);
////
////// Get result of the reply
////
//        System.out.println(clientVersion.getWeb3ClientVersion());

    }
}
