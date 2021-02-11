package com.altran.employee;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservation")
public class EmployeeController {
    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;
    @Autowired
    AmazonSQSAsync amazonSQSAsync;
    @Value("${cloud.aws.end-point.uri}")
    private String endpoint;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendMessageToFirstQueue(@Valid @RequestBody Reservation reservation) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        String messageAsString = objectMapper.writeValueAsString(reservation);
        System.out.println("Writing message {} to queue {}"+ messageAsString );

       // queueMessagingTemplate.send(endpoint, MessageBuilder.withPayload(messageAsString).build());
        SendMessageResult  sendMessageResult= amazonSQSAsync.sendMessage("http://localhost:4566/000000000000/reservation",messageAsString);
       if(sendMessageResult.getMessageId()!=null){
           return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"we will email you soon about your reservation\"}");
       } else {
           return ResponseEntity.badRequest().body("Invalid Input");
       }
        // return "Success";
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body("{\"message\":\"Invalid Input\"}");
    }
}