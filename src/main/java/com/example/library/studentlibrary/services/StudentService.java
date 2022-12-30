package com.example.library.studentlibrary.services;

import com.example.library.studentlibrary.models.Card;
import com.example.library.studentlibrary.models.Student;
import com.example.library.studentlibrary.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {


    @Autowired
    CardService cardService4;

    @Autowired
    StudentRepository studentRepository4;

    public Student getDetailsByEmail(String email){
        Student student = null;

        return student;
    }

    public Student getDetailsById(int id){
        Student student = null;

        return student;
    }

    public void createStudent(Student student){

    }

    public void updateStudent(Student student){

    }

    public void deleteStudent(int id){
        //Delete student and deactivate corresponding card
    }
}