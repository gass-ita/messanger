package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.Debugger;

public class Server {
    private static final int PORT = 6900;


    private static int clientsConnected = 1;
    private static ExecutorService pool = Executors.newFixedThreadPool(clientsConnected); 
    private static ArrayList<ClientsHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);

        while(true){
            Debugger.log("[SERVER] waiting for connection...");
            Socket client = listener.accept();
            ClientsHandler clientThred = new ClientsHandler(client, clients);
            clients.add(clientThred);
            pool.execute(clientThred);
            clientsConnected++;
            pool = Executors.newFixedThreadPool(clientsConnected);
        }
    }
}
