package es.eoi.java2022.recuerdamelon.dto;

import es.eoi.java2022.recuerdamelon.data.entity.ChatMessage;

public class MessagesDTO {
    private Integer id;

    private ChatMessage.MessageType type;
    private String content;
    private String sender;

    //private Integer userId_reciver;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ChatMessage.MessageType getType() {
        return type;
    }

    public void setType(ChatMessage.MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
