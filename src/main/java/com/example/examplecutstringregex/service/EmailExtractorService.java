package com.example.examplecutstringregex.service;

import com.example.examplecutstringregex.entity.Email;
import com.example.examplecutstringregex.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailExtractorService {

    private final EmailRepository emailRepository;

    @Autowired
    public EmailExtractorService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<String> extractAndSaveEmails(String text) {
        List<String> extractedEmails = extractEmails(text);

        for (String email : extractedEmails) {
            saveEmail(email);
        }

        return extractedEmails;
    }

    private List<String> extractEmails(String text) {
        List<String> extractedEmails = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            extractedEmails.add(matcher.group());
        }

        return extractedEmails;
    }

    private void saveEmail(String email) {
        if (!emailRepository.existsByEmail(email)) {
            Email newEmail = new Email();
            newEmail.setEmail(email);
            emailRepository.save(newEmail);
        }
    }
}
