package com.example.library.studentlibrary.services;

import com.example.library.studentlibrary.models.*;
import com.example.library.studentlibrary.repositories.BookRepository;
import com.example.library.studentlibrary.repositories.CardRepository;
import com.example.library.studentlibrary.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Value("${books.max_allowed}")
    int max_allowed_books;

    @Value("${books.max_allowed_days}")
    int getMax_allowed_days;

    @Value("${books.fine.per_day}")
    int fine_per_day;

    public String issueBook(int cardId, int bookId) throws Exception {
        //check whether bookId and cardId already exist
        //conditions required for successful transaction of issue book:
        Card card = cardRepository.findById(cardId).get();
        Book book = bookRepository.findById(bookId).get();


        //1. book is present and available

        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setCard(card);
        if(!book.isAvailable())
            transaction.setTransactionStatus(TransactionStatus.FAILED);
        else
            throw new Exception("Book is either unavailable or not present");

        // If it fails: throw new Exception("Book is either unavailable or not present");
        //2. card is present and activated
        if(card.getCardStatus().equals(CardStatus.DEACTIVATED))
            transaction.setTransactionStatus(TransactionStatus.FAILED);
        else
            throw new Exception("Card is invalid");

        // If it fails: throw new Exception("Card is invalid");
        //3. number of books issued against the card is strictly less than max_allowed_books

        if(card.getBooks().size() >= max_allowed_books)
            transaction.setTransactionStatus(TransactionStatus.FAILED);
        else
            throw new Exception("Book limit has reached for this card");

        // If it fails: throw new Exception("Book limit has reached for this card");
        //If the transaction is successful, save the transaction to the list of transactions and return the id

        //Note that the error message should match exactly in all cases

        book.setCard(card);
        book.setAvailable(false);
        List<Book> list = new ArrayList<>();
        list = card.getBooks();
        list.add(book);
        card.setBooks(list);

        bookRepository.updateBook(book);
        transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        transactionRepository.save(transaction);


        return transaction.getTransactionId(); //return transactionId instead
    }

    public Transaction returnBook(int cardId, int bookId) throws Exception{

        List<Transaction> transactions = transactionRepository.find(cardId, bookId,TransactionStatus.SUCCESSFUL, true);
        Transaction transaction = transactions.get(transactions.size() - 1);

        //for the given transaction calculate the fine amount considering the book has been returned exactly when this function is called

        Date date=new Date();
        long dif=date.getTime()-transaction.getTransactionDate().getTime();
        int amount=0;
        if(TimeUnit.DAYS.convert(dif,TimeUnit.MILLISECONDS)>getMax_allowed_days){
            amount+=(TimeUnit.DAYS.convert(dif,TimeUnit.MILLISECONDS)-getMax_allowed_days)*fine_per_day;
        }

        //make the book available for other users
        bookRepository.findById(bookId).get().setAvailable(true);


        //make a new transaction for return book which contains the fine amount as well
        Transaction returnBookTransaction  = Transaction.builder().
                transactionStatus(TransactionStatus.SUCCESSFUL).
                fineAmount(amount).
                book(bookRepository.findById(bookId).get()).
                card(cardRepository.findById(cardId).get()).
                build();

        return returnBookTransaction; //return the transaction after updating all details
    }
}