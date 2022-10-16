package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import common.Debugger;



public class ClientReciver implements Runnable{
    BufferedReader in;
    Socket server;
    ArrayList<ClientReciverInterface> listeners = new ArrayList<>();

    ClientReciver(Socket server) throws IOException{
        this.server = server;
        in = new BufferedReader(new InputStreamReader(this.server.getInputStream()));
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run()  {
        try {
            while(!server.isClosed()){
                String s = in.readLine();
                if(s == null) continue;
                for (ClientReciverInterface clientInterface : listeners) {
                    clientInterface.textRecived(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        
    }

    public void addListener(ClientReciverInterface newCI){
        listeners.add(newCI);
    }
}