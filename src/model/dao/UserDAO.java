package model.dao;

import connection.ConnectionFactory;
import java.sql.*;
import model.bean.User;

public class UserDAO {
    public void create(User user) throws SQLException {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("INSERT INTO USUARIO (USUARIO_LOGIN , USUARIO_SENHA , NICK) VALUES(x , x , x)");
            
            stmt.setString(1 , user.getUserLogin());
            stmt.setString(2 , user.getUserPassword());
            stmt.setString(3, user.getUserNick());
            
            stmt.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }
}
