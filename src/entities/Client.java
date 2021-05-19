package entities;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class Client implements Runnable{
    private ClientSocket clientSocket;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String nick;
    
    public void start() throws IOException {
        try {
            setNick();
            socket = new Socket("localhost" , 5555); // Criando um objeto do tipo socket e passando o ip da maquina e a porta
            clientSocket = new ClientSocket(socket);
            System.out.println("Local Add: " + socket.getLocalSocketAddress());
            new Thread(this).start();
            messageLoop(clientSocket);
        } finally {
            clientSocket.close();
        }
          
    }
    
    private void messageLoop(ClientSocket clientSocket) {
        String msg;
        
        do {
            msg = JOptionPane.showInputDialog(null, this.nick);
            clientSocket.sendMsg(msg);
            System.out.println("Eu: " + msg);
        } while(!msg.equalsIgnoreCase("close")); 
    }
    
    @Override
    public void run() {
        String msg;
        
        while((msg = clientSocket.getMessage()) != null) {
            System.out.println(msg);
        }
    }
    
    public void setNick() {
        this.nick = JOptionPane.showInputDialog(null , "Nick");
    }
    
    public static void main(String[] args) {
        try{
           Client client = new Client();
           client.start(); 
        } catch(IOException e) {
            System.out.println("Erro cliente: " + e);
        }       
    }
}
