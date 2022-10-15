package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSender implements ClientSenderInterface{

    PrintWriter out;
    Socket server;

    ClientSender(Socket server) throws IOException{
        this.server = server;
        out = new PrintWriter(server.getOutputStream(), true);
    }

    @Override
    public void toSend(String s) {
        out.println(s);        
    }


}