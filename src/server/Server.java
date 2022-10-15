package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import common.Debugger;

public class Server {
    private static final int PORT = 6900;

    private static PrintWriter out;
    private static BufferedReader in;


    public static void main(String[] args) throws IOException {
        Debugger.log("[SERVER] waiting for the client...");
        ServerSocket listener = new ServerSocket(PORT);
        Socket client = listener.accept();
        Debugger.log("[SERVER] client connected");
        out = new PrintWriter(client.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        
        while(true){
            String s = in.readLine();
            if(s.startsWith("quit")) break;
            out.println(client.getInetAddress() + "\t" + s);
        }
        
        
        client.close();
        listener.close();
    }
}
