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
    CardService cardService;

    @Autowired
    StudentRepository studentRepository;

    public Student getDetailsByEmail(String email){
        Student student = studentRepository.findByEmailId(email);
        return student;
    }

    public Student getDetailsById(int id){
        Student student= studentRepository.findById(id).get();
        return student;
    }

    public void createStudent(Student student){


       Card card= cardService.createAndReturn(student);
//connect with card
       student.setCard(card);
        studentRepository.save(student);
    }

    public void updateStudent(Student student){

        studentRepository.updateStudentDetails(student);


    }

    public void deleteStudent(int id){
        studentRepository.deleteCustom(id);
        cardService.deactivateCard(id);
        //Delete student and deactivate corresponding card
    }
}