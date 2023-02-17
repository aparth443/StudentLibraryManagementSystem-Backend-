package com.example.Library_Management_System.Controllers;

import com.example.Library_Management_System.DTOs.IssueBookRequestDto;
import com.example.Library_Management_System.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @PostMapping("/issueBook")
    public String issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto){
        try{
            return transactionService.issueBook(issueBookRequestDto);
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/getTxnInfo")
    public String getTransactionInfo(@RequestParam("bookId") int bookId,@RequestParam("cardId") int cardId){
        return transactionService.getTransactionInfo(bookId,cardId);
    }
}
