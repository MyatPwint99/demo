package com.ojt.demo1.web.form;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    @ManyToMany(mappedBy = "courses")
    List<Student> students =new ArrayList<>();


}
