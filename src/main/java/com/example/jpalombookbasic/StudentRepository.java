package com.example.jpalombookbasic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByAgeGreaterThan(Integer age);

    List<Student> findByName(String name);
}
