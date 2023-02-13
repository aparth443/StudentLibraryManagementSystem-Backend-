package com.example.Library_Management_System.Services;

import com.example.Library_Management_System.Enums.CardStatus;
import com.example.Library_Management_System.Models.Card;
import com.example.Library_Management_System.Models.Student;
import com.example.Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String createStudent(Student student){
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudent_name(student);
        student.setCard(card);
        studentRepository.save(student);
        return "Student and Card added successfully.";
    }

}
