package com.example.examplecutstringregex.controller;

import com.example.examplecutstringregex.entity.Email;
import com.example.examplecutstringregex.service.EmailExtractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/emails")
public class EmailExtractorController {

    @Autowired
    private final EmailExtractorService emailExtractorService;

    @Autowired
    public EmailExtractorController(EmailExtractorService emailExtractorService) {
        this.emailExtractorService = emailExtractorService;
    }

    @PostMapping("/extract-emails")
    public ResponseEntity<List<String>> extractAndSaveEmails(@RequestBody String inputText) {
        try {
            List<String> extractedEmails = emailExtractorService.extractAndSaveEmails(inputText);
            return new ResponseEntity<>(extractedEmails, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
