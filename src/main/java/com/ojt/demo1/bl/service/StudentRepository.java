package com.ojt.demo1.bl.service;

import com.ojt.demo1.web.form.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
