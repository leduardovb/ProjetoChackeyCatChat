package entities;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;
import model.bean.User;
import model.dao.UserDAO;

public class Client {
    private ClientSocket clientSocket;
    private Socket socket;
    private String nick;
    private User user;
    
    public Client() {
        
    }
    
    public Client(User user) {
        this.user = user;
        try {
            start();
        } catch (IOException ex) {
            System.out.println("Erro ao iniciar o Cliente: " + ex);
        }
        System.out.println("C - OK");
    }
    /*
    private void start() throws IOException {
        try {
            socket = new Socket("localhost" , 5555); // Criando um objeto do tipo socket e passando o ip da maquina e a porta
            clientSocket = new ClientSocket(socket);
            new Thread(this).start();
            messageLoop(clientSocket);
        } catch(IOException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            clientSocket.close();
            close();
        } 
    }*/
    
    private void start() throws IOException {
        try {
            socket = new Socket("localhost" , 5555);
            clientSocket = new ClientSocket(socket);
            System.out.println("CLIENTE OK");
        } catch(IOException ex) {
            System.out.println("Erro: " + ex);
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
    
    public boolean sendMessage(String msg) {
        return clientSocket.sendMsg(msg);
    }
    
    /*
    @Override
    public void run() {
        String msg;
        
        while((msg = clientSocket.getMessage()) != null) {
            System.out.println(msg);
        }
    }*/
    
    public void close() {
        UserDAO userDAO = new UserDAO();
        
        userDAO.setStatusOff(user.getUserId());
    }
    
    public User getUser(){
        return this.user;
    }
    
    public ClientSocket getClientSocket(){
        return this.clientSocket;
    }
    
    public static void main(String[] args) {
        User user = new User(1 , "maria" , "dsandias" , "safira" , true);
        
        Client client = new Client(user);
    }
}
