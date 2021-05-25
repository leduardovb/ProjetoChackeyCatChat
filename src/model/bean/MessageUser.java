package model.bean;

public class MessageUser {
    Integer messageId;
    Integer senderUserId;
    Integer addresseeUserId;

    public MessageUser() {
        
    }
    
    public MessageUser(Integer messageId, Integer senderUserId, Integer addresseeUserId) {
        this.messageId = messageId;
        this.senderUserId = senderUserId;
        this.addresseeUserId = addresseeUserId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(Integer senderUserId) {
        this.senderUserId = senderUserId;
    }

    public Integer getAddresseeUserId() {
        return addresseeUserId;
    }

    public void setAddresseeUserId(Integer addresseeUserId) {
        this.addresseeUserId = addresseeUserId;
    }  
}
