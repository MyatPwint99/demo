package com.ojt.demo1.bl.service;

import com.ojt.demo1.web.form.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
