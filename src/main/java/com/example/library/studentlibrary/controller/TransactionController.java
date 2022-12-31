package com.example.library.studentlibrary.controller;

import com.example.library.studentlibrary.models.Transaction;
import com.example.library.studentlibrary.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Add required annotations
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    //Add required annotations
    @Autowired
    TransactionService transactionService;

    @PostMapping("/issueBook")
    public ResponseEntity<String> issueBook(@RequestParam("cardId") int cardId, @RequestParam("bookId") int bookId) throws Exception{
        String s= transactionService.issueBook(cardId, bookId);
        return new ResponseEntity<>("transaction id : "+s+" transaction completed", HttpStatus.ACCEPTED);
    }

    //Add required annotations
    @PostMapping("/returnBook")
    public ResponseEntity<String> returnBook(@RequestParam("cardId") int cardId, @RequestParam("bookId") int bookId) throws Exception{
        Transaction transaction=transactionService.returnBook(cardId, bookId);
        return new ResponseEntity<>("transaction completed", HttpStatus.ACCEPTED);
    }
}
