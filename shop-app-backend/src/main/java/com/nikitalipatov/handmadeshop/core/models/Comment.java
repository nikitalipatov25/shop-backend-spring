package com.nikitalipatov.handmadeshop.core.models;

import com.nikitalipatov.handmadeshop.supportingClasses.CommentComposeKey;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "comment")
@IdClass(CommentComposeKey.class)
public class Comment {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @Id
    @Column(name = "product_id")
    private UUID productId;

    private String userName;

    private String text;

    private String date;

    private byte rating;

    private String dateUpdate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private List<Answer> answers;

    public Comment() {
    }

    public Comment(UUID productId, String userName, String text, String date, byte rating, String dateUpdate, List<Answer> answers) {
        this.productId = productId;
        this.userName = userName;
        this.text = text;
        this.date = date;
        this.rating = rating;
        this.dateUpdate = dateUpdate;
        this.answers = answers;
    }

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

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
