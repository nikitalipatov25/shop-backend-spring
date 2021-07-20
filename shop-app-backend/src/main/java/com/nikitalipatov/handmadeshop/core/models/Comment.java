package com.nikitalipatov.handmadeshop.core.models;

import com.nikitalipatov.handmadeshop.supportingClasses.CommentComposeKey;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "comment")
@IdClass(CommentComposeKey.class)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Id
    private UUID productId;

    private String userName;

    private String text;

    private String date;

    private byte rating;

    @Value("false")
    private Boolean changeable;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public Boolean getChangeable() {
        return changeable;
    }

    public void setChangeable(Boolean changeable) {
        this.changeable = changeable;
    }
}
