package entities;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class Client implements Runnable{
    private ClientSocket clientSocket;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
    public void start() throws IOException {
        try {
            socket = new Socket("localhost" , 5555); // Criando um objeto do tipo socket e passando o ip da maquina e a porta
            clientSocket = new ClientSocket(socket);
            new Thread(this).start();
            messageLoop(clientSocket);
        } finally {
            clientSocket.close();
        }
          
    }
    
    private void messageLoop(ClientSocket clientSocket) {
        String msg;
        
        do {
            msg = JOptionPane.showInputDialog(null, clientSocket.getNick());
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
    
    public static void main(String[] args) {
        try{
           Client client = new Client();
           client.start(); 
        } catch(IOException e) {
            System.out.println("Erro cliente: " + e);
        }
        
    }
}
