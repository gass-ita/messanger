package client;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;



public class Client extends JFrame implements ClientReciverInterface{
    
    final String IP = "192.168.1.20";
    final int PORT = 6900;
    
    JTextArea display;
    JTextField text;
    JButton send;

    public Client() throws IOException{
        // create the middle panel components

        display = new JTextArea ();
        display.setEditable ( false ); // set textArea non-editable
        JScrollPane scroll = new JScrollPane ( display );
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        //Add Textarea in to middle panel
        
        
        this.setBounds(100, 100, 600, 450);
        this.setLayout(null);
        
        text = new JTextField();
        send = new JButton();

        scroll.setBounds(50, 50, 500, 300);
        text.setBounds(50, 350, 450, 50);
        send.setBounds(500, 350, 50, 50);
        
        this.add(text);
        this.add (scroll);
        this.add(send);

        this.setResizable(false);
        this.setLocationRelativeTo (null);
        this.setVisible (true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Socket server = new Socket(IP, PORT);
        ClientReciver cr = new ClientReciver(server);
        ClientSender cs = new ClientSender(server);

        send.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.toSend(text.getText());
            }
        });
        cr.addListener(this);
        
        
    }




    public static void main(String[] args) throws IOException {
        new Client();
        

        // My code
        
    }




    @Override
    public void textRecived(String s) {
        display.setText(display.getText() + "\n" + s);      
    }
}
