package com.ojt.demo1.web.form;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String  name;

    @OneToMany(mappedBy = "author")
    List<Book> books;

}
