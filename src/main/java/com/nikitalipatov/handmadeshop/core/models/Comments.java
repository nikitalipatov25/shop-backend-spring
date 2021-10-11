package com.nikitalipatov.handmadeshop.core.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nikitalipatov.handmadeshop.keys.CommentCompositeKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comments")
@Getter
@Setter
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

}
