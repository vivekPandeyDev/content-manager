package com.app.controller;


import com.app.model.MilestoneReachedEvent;
import com.app.model.NotificationEvent;
import com.app.model.PolicyUpdateEvent;
import com.app.service.NotificationProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
    private final NotificationProducer notificationProducer;



    public NotificationController(NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
    }

    @PostMapping
    public ResponseEntity<String> sendLikeNotification(@RequestBody NotificationEvent event) throws JsonProcessingException {
        logger.info("Received notification request: {}", event);
//        notificationProducer.sendPhotoLikedNotification(event);
        notificationProducer.sendPhotoLikedNotification(
                                event,
                        "notification.direct.exchange",
                        "photo_liked");
        return ResponseEntity.ok("Notification sent successfully!");
    }
    @PostMapping
    @RequestMapping("/policy")
    public ResponseEntity<String>sendPolicyUpdate(@RequestBody PolicyUpdateEvent policyUpdateEvent){
        logger.info("recieved and policy update sent",policyUpdateEvent);
        notificationProducer.sendPolicyUpdateNotification(policyUpdateEvent);
        return ResponseEntity.ok("updateed policy sent sucessfully!!0");
    }


    @PostMapping("/milestone")
    public ResponseEntity<String> sendMilestoneNotification(@RequestBody MilestoneReachedEvent event) {
        String routingKey = "milestone_reached.achievement.default"; // Adjust based on subscriber type
        notificationProducer.sendMilestoneNotification(event, routingKey);
        return ResponseEntity.ok("Milestone notification sent!");
    }



//    @PostMapping
//    public ResponseEntity<String> sendNotification(@RequestBody NotificationEvent event) {
//        logger.info("Received event: {}", event);
//
//        switch (event.getEvent()) {
//            case "photo_liked":
//                notificationProducer.sendPhotoLikedNotification(event,
//                        "notification.direct.exchange",
//                        "photo_liked"
//                );
//                logger.info("Published 'photo_liked' event to direct exchange.");
//                break;
//            case "milestone_reached":
//                notificationProducer.sendMilestoneNotification(
//                        "notification.topic.exchange",
//                        "milestone_reached." + event.getPostId(),
//                        event
//                );
//                logger.info("Published 'milestone_reached' event to topic exchange.");
//                break;
//            case "policy_update":
//                notificationProducer.sendPolicyUpdateNotification(
//                        "notification.fanout.exchange",
//                        "",
//
//                );
//                logger.info("Published 'policy_update' event to fanout exchange.");
//                break;
//            default:
//                logger.warn("Unsupported event type: {}", event.getEvent());
//                return ResponseEntity.badRequest().body("Unsupported event type");
//        }
//
//        return ResponseEntity.ok("Notification sent successfully!");
//    }
}











//2.


//import com.app.model.NotificationEvent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/notifications")
//public class NotificationController {
//    @Autowired
//    private final RabbitTemplate rabbitTemplate;
//    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
//
//    public NotificationController(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    @PostMapping
//    public ResponseEntity<String> sendNotification(@RequestBody NotificationEvent event) {
//        logger.info("Received event: {}", event);
//
//        switch (event.getEvent()) {
//            case "photo_liked":
//                rabbitTemplate.convertAndSend(
//                        "notification.direct.exchange",
//                        "photo_liked",
//                        event
//                );
//                logger.info("Published 'photo_liked' event to direct exchange.");
//                break;
//            case "milestone_reached":
//                rabbitTemplate.convertAndSend(
//                        "notification.topic.exchange",
////                        "milestone_reached." + event.getPostId(),
//                        event
//                );
//                logger.info("Published 'milestone_reached' event to topic exchange.");
//                break;
//            case "policy_update":
//                rabbitTemplate.convertAndSend(
//                        "notification.fanout.exchange",
//                        "",
//                        event
//                );
//                logger.info("Published 'policy_update' event to fanout exchange.");
//                break;
//            default:
//                logger.warn("Unsupported event type: {}", event.getEvent());
//                return ResponseEntity.badRequest().body("Unsupported event type");
//        }
//
//        return ResponseEntity.ok("Notification sent successfully!");
//    }
//}





//1.

//package com.app.controller;
//
//import com.app.model.NotificationEvent;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/notifications")
//public class NotificationController {
//
//    private final RabbitTemplate rabbitTemplate;
//
//    public NotificationController(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    @PostMapping
//    public ResponseEntity<String> sendNotification(@RequestBody NotificationEvent event) {
//        rabbitTemplate.convertAndSend("notification.direct.exchange", "photo_liked", event);
//        return ResponseEntity.ok("Notification sent!");
//    }
//}
