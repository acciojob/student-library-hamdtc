package com.example.library.studentlibrary.services;

import com.example.library.studentlibrary.models.Card;
import com.example.library.studentlibrary.models.CardStatus;
import com.example.library.studentlibrary.models.Student;
import com.example.library.studentlibrary.repositories.CardRepository;
import org.hibernate.type.LocalDateTimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Service
public class CardService {

//higit

    @Autowired
    CardRepository cardRepository;

    public Card createAndReturn(Student student){
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);


        card.setCreatedOn(new Date());
        card.setStudent(student);
        cardRepository.save(card);
        //link student with a new card
        return card;
    }

    public void deactivateCard(int student_id){
        cardRepository.deactivateCard(student_id, CardStatus.DEACTIVATED.toString());
    }
}