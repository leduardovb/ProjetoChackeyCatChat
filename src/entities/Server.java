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
    private final ArrayList<ClientSocket> clients = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    
    public void start() throws IOException {
        System.out.println("Servidor iniciado na porta: " + PORT);
        serverSocket = new ServerSocket(PORT);
        clientConnectionLoop();
    }
    
    private void clientConnectionLoop() throws IOException {
        while(true) {
            ClientSocket clientSocket = new ClientSocket(serverSocket.accept());
            clients.add(clientSocket);
            //System.out.println(users.add(clientSocket.getClient(clientQuantity))); Erro 
            clientQuantity ++;
            getUsers();
            connectedUsers();
            new Thread(() -> {
                try {
                    int pos = getPosicao(clientSocket);
                    clientMessageLoop(clientSocket , pos);
                } catch (IOException ex) {
                    System.out.println("Erro: " + ex);
                }
            }).start();
        }
    }
    
    private void clientMessageLoop(ClientSocket clientSocket , int pos) throws IOException {
        String msg;
        try{
            while((msg = clientSocket.getMessage()) != null){
                if(!msg.equalsIgnoreCase("close")){
                    System.out.printf("Client: %s\n" , msg);
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
    
    private int getPosicao(ClientSocket clientSocket) {
        return clients.indexOf(clientSocket);
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
