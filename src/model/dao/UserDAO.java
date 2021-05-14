package model.dao;

import connection.ConnectionFactory;
import java.sql.*;
import model.bean.User;

public class UserDAO {
    public void insert(User user) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO USUARIO (USUARIO_LOGIN , USUARIO_SENHA , NICK) VALUES(x , x , x)");
            
            stmt.setString(1 , user.getUserLogin());
            stmt.setString(2 , user.getUserPassword());
            stmt.setString(3, user.getUserNick());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean getUserAndPassword() {
        
    }
}
