package com.nikitalipatov.handmadeshop.core.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    private UUID id;
    private String name;
    private String description;
    private String image;
    private Date date;
    private Date expirationDate;
    private double discount;
}
