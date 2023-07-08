package com.example.jpalombookbasic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentServices studentServices;

    @PostMapping("/add_student")
    public ResponseEntity addStudent(@RequestBody Student student){
        String response = studentServices.addStudent(student);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get_by_id")
    public ResponseEntity getStudentById(@RequestParam("rollNo") int rollNo){
        Student student = studentServices.getStudentById(rollNo);
        if(student == null){
            return new ResponseEntity<>("Invalid id" , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student,HttpStatus.FOUND);
    }

    // delete a student by roll no
    @DeleteMapping("/delete_by_id")
    public ResponseEntity deleteStudentByRollNo(@RequestParam("rollNo") int rollNo){
        String response = studentServices.deleteStudentByRollNo(rollNo);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    // update the age of a student based on roll no -> rollNo and age as input
    @PutMapping("/update_student_by_id")
    public ResponseEntity updateStudentAgeById(@RequestParam("rollNo")int rollNo,@RequestParam("age")int age){
        Student student = studentServices.updateStudentAgeById(rollNo,age);
        if(student == null){
            return new ResponseEntity<>("Invalid id" , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    // find all the student who have age greater than 25
    @GetMapping("/get_student_greater_than_25")
    public ResponseEntity getAllStudentByAge(){
        List<Student> studentGreaterThan25 = studentServices.getAllStudentByAge(25);
        if(studentGreaterThan25.isEmpty()){
            return new ResponseEntity<>("Can't find any Student age Greater than 25",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentGreaterThan25,HttpStatus.OK);
    }

    // delete all the students
    @DeleteMapping("/delete_all_student")
    public ResponseEntity deleteAllStudent(){
        String response = studentServices.deleteAllStudent();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    // find all the student with a given name
    @GetMapping("/student_by_name")
    public ResponseEntity findAllStudentByName(@RequestParam("name") String name){
        List<Student> allStudentByName = studentServices.findAllStudentByName(name);
        if(allStudentByName.isEmpty()){
            return new ResponseEntity<>("No student found with this name",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allStudentByName,HttpStatus.FOUND);
    }
}
