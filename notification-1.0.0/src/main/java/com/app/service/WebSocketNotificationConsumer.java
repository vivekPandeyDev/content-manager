package com.app.service;//package com.app.service;
//
//import com.app.handler.NotificationWebSocketHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//public class WebSocketNotificationConsumer {
//
//    private final NotificationWebSocketHandler webSocketHandler;
//
//    public WebSocketNotificationConsumer(NotificationWebSocketHandler webSocketHandler) {
//        this.webSocketHandler = webSocketHandler;
//    }
//
//    @RabbitListener(queues = "photo_liked_queue")
//    public void processPhotoLikedNotification(String message) throws IOException {
//        webSocketHandler.broadcast("Photo Liked Notification: " + message);
//    }
//
//    @RabbitListener(queues = "milestone_queue")
//    public void processMilestoneNotification(String message) throws IOException {
//        webSocketHandler.broadcast("Milestone Notification: " + message);
//    }
//
//    @RabbitListener(queues = "policy_update_queue")
//    public void processPolicyUpdateNotification(String message) throws IOException {
//        webSocketHandler.broadcast("Policy Update Notification: " + message);
//    }
//}
//
