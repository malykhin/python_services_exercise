package com.altran.notification;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping
public class NotificationController {

    private static final String CHAR_SET = "UTF-8";
    private static final String SUBJECT = "Test from our application";
    @Autowired
    private final AmazonSimpleEmailService amazonSimpleEmailService;
    public String from = "altranjavademo2@gmail.com";
    private String templateName;
    private String templateData;

    public NotificationController(AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }


    @SqsListener(value = "http://localstack:4566/000000000000/notification", deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    public void listenToSecondQueue(Reservation message) {

        try {
            ObjectMapper objectMapper=new ObjectMapper();
            String messageAsString = objectMapper.writeValueAsString(message);
            System.out.println("@@@@@@@@@@ Received a message on notification queue: {}"+ messageAsString+" @@@@@@@@@@");
            Destination destination = new Destination();

            destination.setToAddresses(Collections.singletonList(message.getEmailId()));
            SendTemplatedEmailRequest templatedEmailRequest = new SendTemplatedEmailRequest();
            templatedEmailRequest.withDestination(destination);
            if(message.isConfirmed())
                templatedEmailRequest.withTemplate("Confirmed");
            else
                templatedEmailRequest.withTemplate("Cancellation");

            templatedEmailRequest.withTemplateData(messageAsString);
            templatedEmailRequest.withSource(from);
            SendTemplatedEmailResult sendTemplatedEmailResult= amazonSimpleEmailService.sendTemplatedEmail(templatedEmailRequest);

            System.out.println("@@@@@@@@@@Email Sent and ID="+sendTemplatedEmailResult.getMessageId()+" @@@@@@@@@@");

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body("{\"message\":\"Invalid Input\"}");
    }
}