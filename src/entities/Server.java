package entities;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import model.bean.User;
import model.dao.UserDAO;

public class Server {
    public static final int PORT = 5555;
    private static int clientQuantity = 0;
    private ServerSocket serverSocket;
    private ArrayList<ClientSocket> clients = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    
    public void start() throws IOException {
        System.out.println("Servidor iniciado na porta: " + PORT);
        serverSocket = new ServerSocket(PORT);
        clientConnectionLoop();
    }
    
    private void clientConnectionLoop() throws IOException {
        while(true) {
            try{
                ClientSocket clientSocket = new ClientSocket(serverSocket.accept());
                System.out.println("SERVIDOR OK");
                clients.add(clientSocket);
                clientQuantity ++;
                getUsers();
                //connectedUsers();
                new Thread(() -> {
                    try {
                        clientMessageLoop(clientSocket);
                    } catch (IOException ex) {
                        System.out.println("Erro: " + ex);
                    }
                }).start();
                System.out.println("S - OK");
            } catch(IOException ex) {
                    System.out.println("Erro: " + ex);
            }
        }
    }
    
    private void clientMessageLoop(ClientSocket clientSocket) throws IOException {
        String msg;
        try{
            while((msg = clientSocket.getMessage()) != null){
                String userSender = msg.substring(0, msg.indexOf(":"));
                String userMsg = msg.substring(msg.indexOf(":") + 2 , msg.length());
                
                if(!msg.equalsIgnoreCase("close")){
                    System.out.printf("%s: %s\n" , userSender , userMsg);
                    sendMsgToAll(clientSocket , msg);
                }
                else{
                    return;
                }
            }
        } finally{
            clientSocket.close(); 
            clientQuantity --;
        }
    }
    
    private void close() throws IOException{
        serverSocket.close();
    }
    
    private void sendMsgToAll(ClientSocket sender , String msg) {
        Iterator<ClientSocket> iterator = clients.iterator();
        
        while(iterator.hasNext()) {
            ClientSocket clientSocket = iterator.next();
            
            clientSocket.sendMsg(msg);
            System.out.println("MESSAGE - OK");/*
            if(!clientSocket.equals(sender)) {
                if(!clientSocket.sendMsg(msg)){
                    iterator.remove();
                }
            }*/
        }
    }
    
    private void getUsers() {
        UserDAO userDAO = new UserDAO();
        
        users = userDAO.getUsers();
    }
    
    private void connectedUsers() {
        Iterator<User> iterator = users.iterator();
        
        while(iterator.hasNext()) {
            User user = iterator.next();
            String status = null;
            
            System.out.println("Usuário: " + user.getUserNick());
            if(user.isOnline()) {
                status = "Online";
            }
            else {
                status = "Offline";
            }
            System.out.println("Status: " + status);
        }
    }
       
    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.start();
        } catch (IOException ex) {
            System.out.println("Servidor Fechado");
        }
    }
}
