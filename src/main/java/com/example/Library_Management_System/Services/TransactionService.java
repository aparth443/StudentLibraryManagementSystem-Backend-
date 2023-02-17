package com.example.Library_Management_System.Services;

import com.example.Library_Management_System.DTOs.IssueBookRequestDto;
import com.example.Library_Management_System.Enums.CardStatus;
import com.example.Library_Management_System.Enums.TransactionStatus;
import com.example.Library_Management_System.Models.Book;
import com.example.Library_Management_System.Models.Card;
import com.example.Library_Management_System.Models.Transactions;
import com.example.Library_Management_System.Repositories.BookRepository;
import com.example.Library_Management_System.Repositories.CardRepository;
import com.example.Library_Management_System.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    public String issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception{

        int bookId = issueBookRequestDto.getBookId();
        int cardId = issueBookRequestDto.getCardId();
        Book book = bookRepository.findById(bookId).get();
        Card card = cardRepository.findById(cardId).get();

        Transactions transaction = new Transactions();

        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setIssueOperation(true);
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        if(book == null || book.getIssued() == true){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is either not available or already issued.");
        }
        if(card == null || card.getCardStatus() != CardStatus.ACTIVATED){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Card is either not available or not activated.");
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);

        book.setIssued(true);
        List<Transactions> listOfTransactionsOfBook = book.getTransactionsList();
        listOfTransactionsOfBook.add(transaction);
        book.setTransactionsList(listOfTransactionsOfBook);
        book.setCard(card);

        List<Book> listOfBooksIssued = card.getBooksIssued();
        listOfBooksIssued.add(book);
        card.setBooksIssued(listOfBooksIssued);

        List<Transactions> listOfTransactionsOfCard = card.getTransactionsList();
        listOfTransactionsOfCard.add(transaction);
        card.setTransactionsList(listOfTransactionsOfCard);

        cardRepository.save(card);

        return "Book issued successfully";
    }

    public String getTransactionInfo(int bookId,int cardId){
        return transactionRepository.getTransactionsFromBookAndCard(bookId,cardId).get(0).getTransactionId();
    }
}
