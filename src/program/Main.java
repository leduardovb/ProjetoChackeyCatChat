package program;

import model.bean.User;
import model.dao.UserDAO;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        User user = new User("raicini0250" , "852654" , "MOON" , true);
        User user2 = new User("maria" , "85453" , "safira" , true);
        UserDAO userDAO = new UserDAO();
        
        
        if(userDAO.getUserAndPassword(user.getUserLogin(), user.getUserPassword())) {
            System.out.println("Usuario Cadastrado");
        }
        else{
            System.out.println("Usuario nao cadastrado");
        }
    }
}
