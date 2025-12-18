package com.library.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.entity.BorrowRecord;
import com.library.management.service.BorrowRecordService;

@RestController
public class BorrowRecordcontroller {
    
    private final BorrowRecordService borrowRecordService;

    public BorrowRecordcontroller(BorrowRecordService borrowRecordService){
        this.borrowRecordService = borrowRecordService;
    }

    @PostMapping("/borrow/{userId}/{bookId}")
    public BorrowRecord borrowBook(@PathVariable Long userId, @PathVariable Long bookId){
        return borrowRecordService.borrowBook(userId, bookId);
    }

    @PostMapping("/return/{borrowRecordId}")
    public BorrowRecord returnBook(@PathVariable Long borrowRecordId){
        return borrowRecordService.returnBook(borrowRecordId);
    }

    @GetMapping("/borrow/history/{userId}")
    public List<BorrowRecord> getBorrowHistory(@PathVariable Long userId){
        return borrowRecordService.getBorrowHistoryByUser(userId);
    }
}
