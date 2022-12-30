package com.example.library.studentlibrary.controller;

import com.example.library.studentlibrary.models.Student;
import com.example.library.studentlibrary.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    //Add required annotations\
    @GetMapping("/get_student_by_name/{email}")
    public ResponseEntity getStudentByEmail(@RequestParam("email") String email){
       Student student= studentService.getDetailsByEmail(email);
        return new ResponseEntity<>("Student details printed successfully ", HttpStatus.OK);
    }

    //Add required annotations
    @GetMapping("/get_student_by_id/{email}")
    public ResponseEntity getStudentById(@RequestParam("id") int id){
        Student student= studentService.getDetailsById(id);
        return new ResponseEntity<>("Student details printed successfully ", HttpStatus.OK);
    }

    //Add required annotations
    @PostMapping("/add_student")
    public ResponseEntity createStudent(@RequestBody Student student){
        studentService.createStudent(student); //

        return new ResponseEntity<>("the student is successfully added to the system", HttpStatus.CREATED);
    }

    //Add required annotations
    @PutMapping("/update_student")
    public ResponseEntity updateStudent(@RequestBody Student student){
        studentService.updateStudent(student);
        return new ResponseEntity<>("student is updated", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete_std/{id}")
    //Add required annotations
    public ResponseEntity deleteStudent(@RequestParam("id") int id){
        studentService.deleteStudent(id); //
        return new ResponseEntity<>("student is deleted", HttpStatus.ACCEPTED);
    }


}
