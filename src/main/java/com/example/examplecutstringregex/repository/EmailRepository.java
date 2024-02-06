package com.example.examplecutstringregex.repository;


import com.example.examplecutstringregex.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {
    boolean existsByEmail(String email);

    // Add the save method
    Email save(Email email);
}
