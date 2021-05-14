package connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionFactory {
    private static Connection con = null;
    private static String URL = "jdbc:sqlserver://DESKTOP-B9IM94A:1433;databaseName=CHAKEYCATCHAT";
    private static String dataBaseUserName = "sa";
    private static String dataBasePassword = "201025";
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static Statement stmt = null;
    private ResultSet result = null;
    
    public ConnectionFactory() throws ClassNotFoundException {
        getConnection();
    }
    
    public static Connection getConnection() throws ClassNotFoundException {
        try{
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL , dataBaseUserName , dataBasePassword);
            stmt = con.createStatement();
            System.out.println("Banco Conectado");
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex);
        }
        return con;
    }
}
