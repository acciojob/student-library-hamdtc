package com.example.library.studentlibrary.controller;

import com.example.library.studentlibrary.models.Author;
import com.example.library.studentlibrary.models.Student;
import com.example.library.studentlibrary.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {

    //Write createAuthor API with required annotations
    @Autowired
    AuthorService authorService;
    @PostMapping("/create_author")
    public ResponseEntity<String> addAuthor(@RequestBody Author author)
    {
        authorService.create(author);
        return new ResponseEntity<>("author is successfully added" , HttpStatus.CREATED);
    }
}
