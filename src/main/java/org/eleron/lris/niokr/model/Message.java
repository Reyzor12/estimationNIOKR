package org.eleron.lris.niokr.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="message")
public class Message extends Model{

    /*
    * Message fields
    * */

    @NotNull(message = "Нет данных об отправителе сообщения")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName="id", nullable = false)
    private User user;

    @NotNull(message="Отсутствует текст сообщения")
    @Column(name="message", nullable = false)
    private String message;

    @NotNull(message = "Не указано время отправки сообщения")
    @Column(name="date",nullable=false)
    private Date date;

    /*
    * Getters Setters Methods
    * */

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*
    * Constructors
    * */

    public Message(){
        super();
    }

    public Message(Long id){
        super(id);
    }

    public Message(User user, String message, Date date) {
        super();
        this.user = user;
        this.message = message;
        this.date = date;
    }

    /*
    * Override Methods
    * */

    @Override
    public String toString(){
        return message;
    }

    @Override
    public int hashCode(){
        Long id = this.getId();
        int result = id != null ? id.hashCode() : 0;
        result = result*31 + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Message))return false;

        Message messageO = (Message) o;
        Long id = this.getId();
        Long oId = messageO.getId();
        if(id != null ? !id.equals(oId) : oId != null) return false;
        return message != null ? message.equals(messageO) : messageO == null;
    }

    /*
    * Other methods
    * */

    public String info() {
        StringBuffer sb = new StringBuffer("Message(");
        sb.append("id=").append(this.getId());
        sb.append(",user=").append(user);
        sb.append(",date=").append(date);
        sb.append(",message=").append(message);
        sb.append(")");
        return sb.toString();
    }
}
