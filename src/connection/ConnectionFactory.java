package connection;

import java.sql.*;

public class ConnectionFactory {
    private static Connection con = null;
    private static String URL = "jdbc:sqlserver://DESKTOP-B9IM94A:1433;databaseName=CHAKEYCATCHAT";
    private static String dataBaseUserName = "sa";
    private static String dataBasePassword = "201025";
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static Statement stmt = null;
    private ResultSet result = null;
    
    public ConnectionFactory() {
        
    }
    
    public static Connection getConnection() {
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL , dataBaseUserName , dataBasePassword);    
        } catch (ClassNotFoundException | SQLException | RuntimeException ex) {
            throw new RuntimeException("Erro: " + ex);
        }
    }
    
    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }
    
    public static void closeConnection(Connection con , Statement stmt) {
        closeConnection(con);
        
        try {
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }
    
    public static void closeConnection(Connection con , Statement stmt , ResultSet resultSet) {
        closeConnection(con , stmt);
        
        try {
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }
}
