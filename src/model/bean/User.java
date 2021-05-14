package model.bean;

public class User {
    private Integer userId;
    private String userLogin;
    private String userPassword;
    private String userNick;
    private Boolean status;

    public User() {
        
    }
    
    public User(String userLogin, String userPassword, String userNick) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userNick = userNick;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }  
}
