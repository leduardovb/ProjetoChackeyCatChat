package entities;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import model.bean.User;
import model.dao.UserDAO;

public class Server {
    public static final int PORT = 5555;
    private ServerSocket serverSocket;
    private final ArrayList<ClientSocket> clients = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    
    public void start() throws IOException {
        System.out.println("Servidor iniciado na porta: " + PORT);
        serverSocket = new ServerSocket(PORT);
        getUsers();
        clientConnectionLoop();
    }
    
    private void clientConnectionLoop() throws IOException {
        while(true) {
            ClientSocket clientSocket = new ClientSocket(serverSocket.accept());
            clients.add(clientSocket);
            connectedUsers();
            new Thread(() -> {
                try {
                    clientMessageLoop(clientSocket);
                } catch (IOException ex) {
                    System.out.println("Erro: " + ex);
                }
            }).start();
        }
    }
    
    private void clientMessageLoop(ClientSocket clientSocket) throws IOException {
        String msg;
        try{
            while((msg = clientSocket.getMessage()) != null){
                if(!msg.equalsIgnoreCase("close")){
                    System.out.printf("Cliente: %s\n" , msg);
                    sendMsgToAll(clientSocket , msg);
                }
                else{
                    return;
                }
            }
        } finally{
            clientSocket.close();
            close();
        }
    }
    
    private void close() throws IOException{
        serverSocket.close();
    }
    
    private void sendMsgToAll(ClientSocket sender , String msg) {
        Iterator<ClientSocket> iterator = clients.iterator();
        
        while(iterator.hasNext()) {
            ClientSocket clientSocket = iterator.next();
            
            if(!clientSocket.equals(sender)) {
                if(!clientSocket.sendMsg("Client: " + msg)){
                    iterator.remove();
                }
            }
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
            
            System.out.println(user.getUserNick());
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
