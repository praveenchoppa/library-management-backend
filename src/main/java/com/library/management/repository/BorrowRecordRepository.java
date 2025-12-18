package com.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.entity.BorrowRecord;
import com.library.management.entity.User;

import java.util.List;


public interface BorrowRecordRepository extends JpaRepository<BorrowRecord,Long> {
    
    List<BorrowRecord> findByUser(User user);
}
