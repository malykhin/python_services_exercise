package com.altran.reservation;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ReservationController {
    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;
    @Autowired
    AmazonSQSAsync amazonSQSAsync;
    @Autowired
    ReservationService reservationService;

    @SqsListener(value = "http://localstack:4566/000000000000/reservation", deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    public void listenToSecondQueue(Reservation message) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        System.out.println("@@@@@@@@@@ Received a message on Reservation queue: {}"+ objectMapper.writeValueAsString(message)+" @@@@@@@@@@");
        message.setConfirmed(reservationService.checkAvailability(message));
        if(!message.isConfirmed())
            message.setSlotList(reservationService.availableSlots(message));
        String messageAsString = objectMapper.writeValueAsString(message);
        SendMessageResult  sendMessageResult= amazonSQSAsync.sendMessage("http://localstack:4566/000000000000/notification",messageAsString);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body("{\"message\":\"Invalid Input\"}");
    }
}