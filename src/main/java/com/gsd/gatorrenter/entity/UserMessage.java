package com.gsd.gatorrenter.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Intesar on 3/4/2017.
 */
@Entity
@Table(name = "user_messages")
public class UserMessage {
    private int id;
    private String message;
    private Timestamp created;
    private Boolean isNewMessage;
    private User fromUser;
    private User toUser;
    private Apartment apartment;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "is_new_message")
    public Boolean getIsNewMessage() {
        return isNewMessage;
    }

    public void setIsNewMessage(Boolean isNewMessage) {
        this.isNewMessage = isNewMessage;
    }

    @ManyToOne
    @JoinColumn(name = "from_user_id", referencedColumnName = "uid")
    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    @ManyToOne
    @JoinColumn(name = "to_user_id", referencedColumnName = "uid")
    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    @ManyToOne
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMessage that = (UserMessage) o;

        if (id != that.id) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (isNewMessage != null ? !isNewMessage.equals(that.isNewMessage) : that.isNewMessage != null) return false;
        if (fromUser != null ? !fromUser.equals(that.fromUser) : that.fromUser != null) return false;
        if (toUser != null ? !toUser.equals(that.toUser) : that.toUser != null) return false;
        return !(apartment != null ? !apartment.equals(that.apartment) : that.apartment != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (isNewMessage != null ? isNewMessage.hashCode() : 0);
        result = 31 * result + (fromUser != null ? fromUser.hashCode() : 0);
        result = 31 * result + (toUser != null ? toUser.hashCode() : 0);
        result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
        return result;
    }
}
