package com.example.jpalombookbasic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServices {
    @Autowired
    StudentRepository studentRepository;
    public String addStudent(Student student) {
        studentRepository.save(student);
        return "Student added into Database";
    }

    public Student getStudentById(int rollNo) {
        Optional<Student> optionalStudent = studentRepository.findById(rollNo);
        if(optionalStudent.isPresent()){
            return optionalStudent.get();
        }
        return null;
    }

    public String deleteStudentByRollNo(int rollNo) {
        studentRepository.deleteById(rollNo);
        return "Student deleted from the database";
    }

    public Student updateStudentAgeById(int rollNo, int age) {
        Optional<Student> optionalStudent = studentRepository.findById(rollNo);
        if(!optionalStudent.isPresent()){
            return null;
        }
        Student updateStudent = optionalStudent.get();
        updateStudent.setAge(age);
        studentRepository.save(updateStudent);
        return updateStudent;
    }

    public List<Student> getAllStudentByAge(int age) {
        List<Student> allStudent = studentRepository.findByAgeGreaterThan(age);
        return allStudent;
    }

    public String deleteAllStudent() {
        studentRepository.deleteAll();
        return "Delete all student from Database";
    }

    public List<Student> findAllStudentByName(String name) {
        List<Student> studentsByName = studentRepository.findByName(name);
        return studentsByName;
    }
}
