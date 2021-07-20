package com.nikitalipatov.handmadeshop.core.models;

import com.nikitalipatov.handmadeshop.supportingClasses.ProductUserCompositeKey;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "comment")
@IdClass(ProductUserCompositeKey.class)
public class Comment {

    @Id
    private UUID productId;

    @Id
    private String userName;

    private String text;

    private String date;

    private byte rating;

    @Value("false")
    private Boolean changeable;

    public Comment() {
    }

    public boolean isChangeable(boolean b) {
        return changeable;
    }

    public void setChangeable(boolean changeable) {
        this.changeable = changeable;
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
}
