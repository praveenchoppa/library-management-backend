package com.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
  
}
