package com.example.library.studentlibrary.services;

import com.example.library.studentlibrary.models.Book;
import com.example.library.studentlibrary.models.Genre;
import com.example.library.studentlibrary.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {


    @Autowired
    BookRepository bookRepository;

    public void createBook(Book book){

        bookRepository.save(book);
    }

    public List<Book> getBooks(String genre, boolean available, String author){
        List<Book> books = null; //find the elements of the list by yourself

        if(genre != null && available==true){
            books = bookRepository.findBooksByGenre(genre,available);
        }
        else if(author !=null){
            books = bookRepository.findBooksByAuthor(author,available);
        }
        else if(genre !=null){
            books = bookRepository.findBooksByGenre(genre,available);
        }
        else if(genre !=null && author != null){
            books = bookRepository.findBooksByGenreAuthor(genre,author,available);
        }
        else{
            books = bookRepository.findByAvailability(available);
        }
        return books;
    }
}