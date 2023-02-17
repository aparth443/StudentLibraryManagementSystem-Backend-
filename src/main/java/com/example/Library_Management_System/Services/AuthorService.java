package com.example.Library_Management_System.Services;

import com.example.Library_Management_System.DTOs.AuthorEntryDto;
import com.example.Library_Management_System.DTOs.AuthorResponseDto;
import com.example.Library_Management_System.DTOs.BookResponseDto;
import com.example.Library_Management_System.Models.Author;
import com.example.Library_Management_System.Models.Book;
import com.example.Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String createAuthor(AuthorEntryDto authorEntryDto){

        Author author = new Author();

        author.setName(authorEntryDto.getName());
        author.setAge(authorEntryDto.getAge());
        author.setCountry(authorEntryDto.getCountry());
        author.setRating(authorEntryDto.getRating());
        
        authorRepository.save(author);
        return "Author added successfully";
    }

    public AuthorResponseDto getAuthor(int authorId){
        Author author = authorRepository.findById(authorId).get();

        AuthorResponseDto authorResponseDto = new AuthorResponseDto();

        List<Book> bookList = author.getBooksWritten();
        List<BookResponseDto> bookResponseDtoList = new ArrayList<>();

        for(Book b: bookList){
            BookResponseDto bookResponseDto = new BookResponseDto();
            bookResponseDto.setName(b.getName());
            bookResponseDto.setPages(b.getPages());
            bookResponseDto.setGenre(b.getGenre());

            bookResponseDtoList.add(bookResponseDto);
        }

        authorResponseDto.setName(author.getName());
        authorResponseDto.setAge(author.getAge());
        authorResponseDto.setBooksWritten(bookResponseDtoList);
        authorResponseDto.setRating(author.getRating());
        authorResponseDto.setCountry(author.getCountry());
        return authorResponseDto;
    }
}
