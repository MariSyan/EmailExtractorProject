package com.example.examplecutstringregex;

import com.example.examplecutstringregex.entity.Email;
import com.example.examplecutstringregex.repository.EmailRepository;
import com.example.examplecutstringregex.service.EmailExtractorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = "com.example.examplecutstringregex.service")
public class EmailExtractorServiceTest {

    @InjectMocks
    private EmailExtractorService emailExtractorService;

    @Mock
    private EmailRepository emailRepository;

    @Test
    public void testExtractAndSaveEmails() {
        // Mock the behavior of existsByEmail method
        when(emailRepository.existsByEmail(anyString())).thenReturn(false);

        String inputText = "Word1 word2 email1@gmail.com. Word3 word4 email2@gmail.com. Word5 word6";
        List<String> extractedEmails = emailExtractorService.extractAndSaveEmails(inputText);

        // Add assertions based on your test scenario
        assertThat(extractedEmails).hasSize(2);

        // Verify that existsByEmail method is called twice with any string
        verify(emailRepository, times(2)).existsByEmail(anyString());

        // Verify that the save method is called twice with any Email object
        verify(emailRepository, times(2)).save(any(Email.class));
    }
}