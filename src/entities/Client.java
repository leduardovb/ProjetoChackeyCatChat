package entities;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;
import model.bean.User;
import model.dao.UserDAO;

public class Client implements Runnable{
    private ClientSocket clientSocket;
    private Socket socket;
    private String nick;
    private User user;
    
    public Client() {
        
    }
    
    public Client(User user) {
        this.user = user;
    }
    
    public void start() throws IOException {
        try {
            setNick();
            socket = new Socket("localhost" , 5555); // Criando um objeto do tipo socket e passando o ip da maquina e a porta
            clientSocket = new ClientSocket(socket , user);
            new Thread(this).start();
            messageLoop(clientSocket);
        } catch(IOException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            clientSocket.close();
            close();
        } 
    }
    
    private void messageLoop(ClientSocket clientSocket) {
        String msg;
        
        do {
            msg = JOptionPane.showInputDialog(null, this.nick);
            clientSocket.sendMsg(msg);
            System.out.println(user.getUserNick() +": " + msg);
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
        this.nick = user.getUserNick();
    }
    
    public void close() {
        UserDAO userDAO = new UserDAO();
        
        userDAO.setStatusOff(user.getUserId());
    }
    
    public User getUser(){
        return this.user;
    }
    
    public static void main(String[] args) {
        try{
            User user = new User();
            user.setUserNick("M O O N");
            Client client = new Client(user);
            client.start(); 
        } catch(IOException e) {
            System.out.println("Erro cliente: " + e);
        }       
    }
}
