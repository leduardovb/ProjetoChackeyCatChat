package model.dao;

import connection.ConnectionFactory;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.User;

public class UserDAO {
    public void insert(User user) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO USUARIO (USUARIO_LOGIN , USUARIO_SENHA , NICK , USER_STATUS) VALUES(? , ? , ? , ?)");
            
            stmt.setString(1 , user.getUserLogin());
            stmt.setString(2 , user.getUserPassword());
            stmt.setString(3, user.getUserNick());
            stmt.setBoolean(4, user.isStatus());
            stmt.executeUpdate();
            System.out.println("Insert Concluido");
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean getUserAndPassword(String user , String password) {
        Connection con = ConnectionFactory.getConnection(); 
        
        try {
            Statement stmt = null;
            ResultSet resultSet = null;
            stmt = con.createStatement();
            resultSet = stmt.executeQuery("SELECT USUARIO_LOGIN , USUARIO_SENHA FROM USUARIO WHERE USUARIO_LOGIN = '" + user + "' AND USUARIO_SENHA = '" + password + "'");
            
            String us = "" , pa = "";
            
            while(resultSet.next()){
                us = resultSet.getString("USUARIO_LOGIN");
                pa = resultSet.getString("USUARIO_SENHA");
            }
            
            return us.equals(user) && pa.equals(password);
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
        
        return false;
    }
}
