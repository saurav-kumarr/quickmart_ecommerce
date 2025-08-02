package com.quickmart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long productId;
     private String ProductName;
     private String image;
     private String description;
     private Integer quantity;
     private Integer price;
     private double discount;
     private double specialPrice;

     @ManyToOne
     @JoinColumn(name = "category_id")
     private Category category;

}
