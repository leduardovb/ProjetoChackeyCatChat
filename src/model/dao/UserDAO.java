package model.dao;

import connection.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
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
            stmt.setBoolean(4, user.isOnline());
            stmt.executeUpdate();
            System.out.println("Insert Concluido");
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean getUserAndPassword(String user , String password) {
        Connection con = ConnectionFactory.getConnection();
        Statement stmt = null;
        ResultSet resultSet = null;
        
        try {
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
        } finally {
            ConnectionFactory.closeConnection(con, stmt, resultSet);
        }
        
        return false;
    }
    
    public boolean isAlreadyUser(String user) {
        Connection con = ConnectionFactory.getConnection();
        Statement stmt = null;
        ResultSet resultSet = null;
        
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery("SELECT USUARIO_LOGIN FROM USUARIO WHERE USUARIO_LOGIN = '" + user + "'");
            
            String rs = null;
            
            while(resultSet.next()) {
                rs = resultSet.getString("USUARIO_LOGIN");
            }
            
            return rs == null;
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, resultSet);
        }
        
        return false;
    }
    
    public int authenticatorCod() {
        Random alt = new Random();
        
        int num = alt.nextInt((9999 - 1000) + 1) - 1000 ;
        
        return num;
    }
    
    public ArrayList getUsers() {
        ArrayList<User> users = new ArrayList<>();
        Connection con = ConnectionFactory.getConnection();
        Statement stmt = null;
        ResultSet resultSet = null;
        
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery("SELECT ID_USUARIO, NICK , USER_STATUS FROM USUARIO");
            
            Integer idUser;
            String nick;
            Boolean status;
            
            while(resultSet.next()) {
                User user = new User();
                idUser = resultSet.getInt("ID_USUARIO");
                nick = resultSet.getString("NICK");
                status = resultSet.getBoolean("USER_STATUS");
                
                user.setUserId(idUser);
                user.setUserNick(nick);
                user.setStatus(status);
                
                users.add(user);
            }
        } catch(SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, resultSet);
        }
        
        return users;
    }
    
    public User getUser(String username , String password) {
        Connection con = ConnectionFactory.getConnection();
        Statement stmt = null;
        ResultSet resultSet = null;
        
        try {   
            stmt = con.createStatement();
            resultSet = stmt.executeQuery("SELECT ID_USUARIO , NICK , USUARIO_LOGIN , USUARIO_SENHA FROM USUARIO WHERE USUARIO_LOGIN = '" + username + "' AND USUARIO_SENHA = '" + password + "'");
            
            String nick = "" , userLogin = "" , userPass = "";
            Integer userId = 0;
            
            while(resultSet.next()) {
                nick = resultSet.getString("NICK");
                userLogin = resultSet.getString("USUARIO_LOGIN");
                userPass = resultSet.getString("USUARIO_SENHA");
                userId = resultSet.getInt("ID_USUARIO");
            }
            
            User user = new User(userId , userLogin , userPass , nick , true);
            
            setStatusOn(user.getUserId());
            
            return user;
            
        } catch(SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con , stmt , resultSet);
        }
        return null;
    }
    
    public void setStatusOn(Integer id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE USUARIO SET USER_STATUS = 1 WHERE ID_USUARIO = '" + id +"'");
            
            stmt.executeUpdate();
            System.out.println("Usuario Online");
        } catch(SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void setStatusOff(Integer id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE USUARIO SET USER_STATUS = 0 WHERE ID_USUARIO = '" + id +"'");
            stmt.executeUpdate();
            System.out.println("Usuario Offline");
        } catch(SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
