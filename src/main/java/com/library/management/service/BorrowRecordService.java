package com.library.management.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.library.management.entity.Book;
import com.library.management.entity.BorrowRecord;
import com.library.management.entity.User;
import com.library.management.exception.BadRequestException;
import com.library.management.exception.ResourceNotFoundException;
import com.library.management.repository.BookRepository;
import com.library.management.repository.BorrowRecordRepository;
import com.library.management.repository.UserRepository;

@Service
public class BorrowRecordService {
    
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    public BorrowRecordService(BorrowRecordRepository borrowRecordRepository,UserRepository userRepository,BookRepository bookRepository){
        this.borrowRecordRepository=borrowRecordRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;     
    }

    public BorrowRecord borrowBook(Long userId,Long bookId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        if(book.getAvailableCopies() <= 0){
            throw new BadRequestException("Book not available");
        }

        book.setAvailableCopies(book.getAvailableCopies() -1);
        bookRepository.save(book);

        BorrowRecord record = new BorrowRecord();
        record.setUser(user);
        record.setBook(book);
        record.setBorrowDate(LocalDate.now());
        record.setStatus( "BORROWED");

        return borrowRecordRepository.save(record);
    }

    public BorrowRecord returnBook(Long borrowRecordId){

        BorrowRecord record = borrowRecordRepository.findById(borrowRecordId).orElseThrow(() -> new ResourceNotFoundException("Borrow record not found"));

        if(!record.getStatus().equals("BORROWED")){
           throw new BadRequestException("Book already returned");
        }

        Book book = record.getBook(); 

        book.setAvailableCopies(book.getAvailableCopies() +1);
        bookRepository.save(book);

        record.setStatus("RETURNED");
        record.setReturnDate(LocalDate.now());

        return borrowRecordRepository.save(record);
    }

    public List<BorrowRecord> getBorrowHistoryByUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return borrowRecordRepository.findByUser(user);
    }

}
