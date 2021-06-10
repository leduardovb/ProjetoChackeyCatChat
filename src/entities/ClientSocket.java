package entities;

import java.io.*;
import java.net.*;

public class ClientSocket {
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;
    
    public ClientSocket(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //Criando um obj do tipo br para escrever as trocas de mensagens. 
        this.out = new PrintWriter(socket.getOutputStream() , true); // Criando um obj do tipo pw e passando um metódo do cs que le em bytes e passando true para realizar o auto flush
    }
    
    public String getMessage() {
        try {
            return in.readLine();
        }catch(IOException e) {
            return null;
        }
    }
    
    public boolean sendMsg(String msg) {
        out.print(msg);
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