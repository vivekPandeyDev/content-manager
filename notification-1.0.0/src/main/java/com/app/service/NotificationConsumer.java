package com.app.service;


import com.app.handler.NotificationWebSocketHandler;
import com.app.model.MilestoneReachedEvent;
import com.app.model.NotificationEvent;
import com.app.model.PolicyUpdateEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.DataInput;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {

    private static final Logger logger = LoggerFactory.getLogger(NotificationConsumer.class);
    private final NotificationWebSocketHandler webSocketHandler;
    private final ObjectMapper objectMapper;

//    public NotificationConsumer(NotificationWebSocketHandler webSocketHandler, ObjectMapper objectMapper) {
//        this.webSocketHandler = webSocketHandler;
////        this.userSessionService = userSessionService;
//        this.objectMapper = objectMapper;
//    }

    @RabbitListener(queues = "photo_liked_queue")
    public void processPhotoLikedNotification(NotificationEvent message) {//notificaation event
        try {
            // Deserialize the message to NotificationEvent
//            NotificationEvent event = objectMapper.readValue((DataInput) message, NotificationEvent.class);

            logger.info("Photo Liked Notification Received: {}", message);

            // Send notification to the specific user
            String notificationMessage = String.format(
                    "Hi %s, your photo '%s' was liked by %s!",
                    message.getOwnerUserName(),
                    message.getPhotoTitle(),
                    message.getLikedByUsername()
            );
            webSocketHandler.sendNotificationToUser(message.getOwnerUserName(), notificationMessage);
        } catch (Exception e) {
            logger.error("Error processing Photo Liked notification: {}", e.getMessage());
        }
    }
    @RabbitListener(queues = "policy_update_queue")
    public void policyUpdate(String message) {//notificaation event
        try {
            // Deserialize the message to NotificationEvent
            PolicyUpdateEvent policyUpdateEvent = objectMapper.readValue(message, PolicyUpdateEvent.class);

            logger.info("Photo Liked Notification Received: {}", policyUpdateEvent);

            // Send notification to the specific user
            String notificationMessage = String.format(
                    "Hi %s, your photo '%s' was liked by %s!",
                    policyUpdateEvent.getPolicyType(),
                    policyUpdateEvent.getPolicyVersion(),
                    policyUpdateEvent.getEvent()
            );
            webSocketHandler.sendNotificationToPolicyUpdate(notificationMessage);
        } catch (Exception e) {
            logger.error("Error processing Photo Liked notification: {}", e.getMessage());
        }
    }

//    @RabbitListener(queues = "milestone_queue")
//    public void processMilestoneNotification(String message) {
//        try {
//            MilestoneReachedEvent event = objectMapper.readValue(message, MilestoneReachedEvent.class);
//            logger.info("Milestone Notification Received: {}", event);
//
//            // Forward to WebSocket topic
//
//            webSocketHandler.topicMilestone("",event);
//
//        } catch (Exception e) {
//            logger.error("Error processing Milestone notification: {}", e.getMessage());
//        }
//    }


    @RabbitListener(queues = "achievement_notification_queue")
    public void processAchievementNotification(String message) {
        try {
            MilestoneReachedEvent event = objectMapper.readValue(message, MilestoneReachedEvent.class);
            logger.info("Achievement Notification: {}", event);
            // Add logic to notify users about achievements
            webSocketHandler.sendMilestoneNotificationToOwner(event);
        } catch (Exception e) {
            logger.error("Error processing Achievement Notification: {}", e.getMessage());
        }
    }

    @RabbitListener(queues = "analytics_queue")
    public void processAnalyticsNotification(String message) {
        try {
            MilestoneReachedEvent event = objectMapper.readValue(message, MilestoneReachedEvent.class);
            logger.info("Analytics Event: {}", event);
            // Add logic to log analytics data
        } catch (Exception e) {
            logger.error("Error processing Analytics Notification: {}", e.getMessage());
        }
    }

    @RabbitListener(queues = "leaderboard_queue")
    public void processLeaderboardNotification(String message) {
        try {
            MilestoneReachedEvent event = objectMapper.readValue(message, MilestoneReachedEvent.class);
            logger.info("Leaderboard Update: {}", event);
            // Add logic to update leaderboard
        } catch (Exception e) {
            logger.error("Error processing Leaderboard Notification: {}", e.getMessage());
        }
    }
}












//import com.app.handler.NotificationWebSocketHandler;
//import com.app.model.NotificationEvent;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class NotificationConsumer {
//
//    private static final Logger logger = LoggerFactory.getLogger(NotificationConsumer.class);
//    private final NotificationWebSocketHandler webSocketHandler;
//    private final ObjectMapper objectMapper;
//
//    public NotificationConsumer(NotificationWebSocketHandler webSocketHandler, ObjectMapper objectMapper) {
//        this.webSocketHandler = webSocketHandler;
//        this.objectMapper = objectMapper;
//    }
//
//    @RabbitListener(queues = "photo_liked_queue")
//    public void processPhotoLikedNotification(String message) {
//        try {
//            NotificationEvent event = objectMapper.readValue(message, NotificationEvent.class);
//            logger.info("Photo Liked Notification Received: {}", event);
//            webSocketHandler.sendNotificationToUser(event.getOwnerUserName(), "Your photo was liked by " + event.getLikedByUsername());
//        } catch (Exception e) {
//            logger.error("Error processing Photo Liked notification: {}", e.getMessage());
//        }
//    }
//}
//










//package com.app.service;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
//import com.app.model.MilestoneReachedEvent;
//import com.app.model.NotificationEvent;
//import com.app.model.PolicyUpdateEvent;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class NotificationConsumer {
//
//    private static final Logger logger = LoggerFactory.getLogger(NotificationConsumer.class);
//    private final SimpMessagingTemplate messagingTemplate;
//    private final ObjectMapper objectMapper;
//
//    public NotificationConsumer(SimpMessagingTemplate messagingTemplate, ObjectMapper objectMapper) {
//        this.messagingTemplate = messagingTemplate;
//        this.objectMapper = objectMapper;
//    }
//
//    @RabbitListener(queues = "photo_liked_queue")
//    public void processPhotoLikedNotification(String message) {
//        try {
//            NotificationEvent event = objectMapper.readValue(message, NotificationEvent.class);
//            logger.info("Photo Liked Notification Received: {}", event);
//            // Forward to WebSocket topic
//            messagingTemplate.convertAndSend("/topic/photo_liked", event);
//        } catch (Exception e) {
//            logger.error("Error processing Photo Liked notification: {}", e.getMessage());
//        }
//    }
//
//    @RabbitListener(queues = "milestone_queue")
//    public void processMilestoneNotification(String message) {
//        try {
//            MilestoneReachedEvent event = objectMapper.readValue(message, MilestoneReachedEvent.class);
//            logger.info("Milestone Notification Received: {}", event);
//            // Forward to WebSocket topic
//            messagingTemplate.convertAndSend("/topic/milestone_reached", event);
//        } catch (Exception e) {
//            logger.error("Error processing Milestone notification: {}", e.getMessage());
//        }
//    }
//
//    @RabbitListener(queues = "policy_update_queue")
//    public void processPolicyUpdateNotification(String message) {
//        try {
//            PolicyUpdateEvent event = objectMapper.readValue(message, PolicyUpdateEvent.class);
//            logger.info("Policy Update Notification Received: {}", event);
//            // Forward to WebSocket topic
//            messagingTemplate.convertAndSend("/topic/policy_update", event);
//        } catch (Exception e) {
//            logger.error("Error processing Policy Update notification: {}", e.getMessage());
//        }
//    }
//}
//
//
//
////@Service
////public class NotificationConsumer {
////
////    @RabbitListener(queues = "photo_liked_queue")
////    public void processPhotoLikedNotification(String message) {
////        System.out.println("Photo Liked Notification Received: " + message);
////        // Send notification to the photo owner
////    }
////
////    @RabbitListener(queues = "milestone_queue")
////    public void processMilestoneNotification(String message) {
////        System.out.println("Milestone Notification Received: " + message);
////        // Notify the post owner
////    }
////
////    @RabbitListener(queues = "policy_update_queue")
////    public void processPolicyUpdateNotification(String message) {
////        System.out.println("Policy Update Notification Received: " + message);
////        // Broadcast to all users
////    }
////}
////
