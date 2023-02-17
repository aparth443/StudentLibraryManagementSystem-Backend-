package com.example.Library_Management_System.Services;

import com.example.Library_Management_System.DTOs.BookRequestDto;
import com.example.Library_Management_System.Models.Author;
import com.example.Library_Management_System.Models.Book;
import com.example.Library_Management_System.Repositories.AuthorRepository;
import com.example.Library_Management_System.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;
    public String addBook(BookRequestDto bookRequestDto){
        int authorId = bookRequestDto.getAuthorId();
        Author author = authorRepository.findById(authorId).get();

        Book book = new Book();
        book.setAuthor(author);
        book.setGenre(bookRequestDto.getGenre());
        book.setName(bookRequestDto.getName());
        book.setPages(bookRequestDto.getPages());
        book.setIssued(false);

        List<Book> currentBooksWritten = author.getBooksWritten();
        currentBooksWritten.add(book);
        author.setBooksWritten(currentBooksWritten);

        authorRepository.save(author);
        //bookrepo.save is not required as it is autosaved using cascading effect.

        return "Book added successfully";
    }
}
