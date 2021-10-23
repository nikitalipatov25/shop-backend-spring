package com.nikitalipatov.handmadeshop.core.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nikitalipatov.handmadeshop.keys.CommentCompositeKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "newcomments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CommentCompositeKey.class)
public class Comments {
    @Id
    private UUID productId;
    @Id
    private Long userId;
    private String text;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;
    private double rating;
    private String userName;
    @ManyToMany
    @JoinTable(name = "comment_answers",
            joinColumns = {@JoinColumn(name = "prod_id"), @JoinColumn(name = "user_id")},
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private Set<Answer> answers;

}
