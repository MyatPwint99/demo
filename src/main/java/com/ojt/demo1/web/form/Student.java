package com.ojt.demo1.web.form;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "student_course",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    List<Course> courses = new ArrayList<>();

//    @ManyToMany
//    List<Course> courses = new ArrayList<>();

}
