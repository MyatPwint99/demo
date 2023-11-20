package com.ojt.demo1.web.form;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String  title;
    String  body;
    @ManyToOne
    @JoinColumn(name = "author_id")
    Author author;
}
