package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import common.Debugger;

public class ClientsHandler implements Runnable{
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientsHandler> clients;

    public ClientsHandler(Socket client, ArrayList<ClientsHandler> clients) throws IOException{
        this.client = client;
        in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
        out = new PrintWriter(this.client.getOutputStream(), true);
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            while(true){
                String request = in.readLine();
                Debugger.log(request);
                if(request.startsWith("quit")) break;
                if(request.startsWith("br")) broadcast(request.substring(3));
                out.println(client.getInetAddress() + "\t" + request);
            }
        } catch (Exception e) {
        }
    }

    public void broadcast(String s){
        for (ClientsHandler c : clients) {
            c.out.println(client.getInetAddress() + " says to everyone: " + s);
        }
    }
    
}
