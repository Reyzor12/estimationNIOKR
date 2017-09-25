package org.eleron.lris.niokr.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="message")
public class Message extends Model{

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User user;

    @NotNull(message="Это поле не может быть пустым")
    @Column(name="message", nullable = false)
    private String message;

    @NotNull(message = "Время не может быть пустым")
    @Column(name="date",nullable=false)
    private Date date;

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

    public Message(){
        super();
    }

    public Message(Long id){
        super(id);
    }
}
