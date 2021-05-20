package entities;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import model.bean.User;

public class ClientSocket {
    private static ArrayList<User> users = new ArrayList<>();
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;
    
    public ClientSocket(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //Criando um obj do tipo br para escrever as trocas de mensagens. 
        this.out = new PrintWriter(socket.getOutputStream() , true); // Criando um obj do tipo pw e passando um metódo do cs que le em bytes e passando true para realizar o auto flush
    }
    
    public ClientSocket(Socket socket , User user) throws IOException {
        users.add(user);
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
        this.out = new PrintWriter(socket.getOutputStream() , true); 
    }
    
    public String getMessage() {
        try {
            return in.readLine();
        }catch(IOException e) {
            return null;
        }
    }
    
    public User getClient(int pos) {
        return users.get(pos);
    }
    
    public boolean sendMsg(String msg) {
        out.println(msg);
        return !out.checkError();
    }
    
    public SocketAddress getLocalSocketAdress() {
        return socket.getLocalSocketAddress();
    }
    
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}