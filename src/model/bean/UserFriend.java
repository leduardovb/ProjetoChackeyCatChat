package model.bean;

public class UserFriend {
    private Integer userId;
    private Integer userFriendId;
    private Boolean status;

    public UserFriend() {
        
    }
    
    public UserFriend(Integer userId, Integer userFriendId, Boolean status) {
        this.userId = userId;
        this.userFriendId = userFriendId;
        this.status = status;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserFriendId() {
        return userFriendId;
    }

    public void setUserFriendId(Integer userFriendId) {
        this.userFriendId = userFriendId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
