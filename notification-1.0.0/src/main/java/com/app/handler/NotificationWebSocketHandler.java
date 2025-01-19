package com.app.handler;

import com.app.model.MilestoneReachedEvent;
import com.app.service.NotificationConsumer;
import com.app.service.UserSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class NotificationWebSocketHandler extends TextWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(NotificationWebSocketHandler.class);


    // Map to store user sessions
    // private final ConcurrentHashMap<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();
//        UserSessionService userSessionService=newYYY UserSessionService();
        public NotificationWebSocketHandler(){

        }
//    public NotificationWebSocketHandler(UserSessionService userSessionService) {
//        this.userSessionService = userSessionService;
//    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        try {
            System.out.println("Session Established: " + session.getId());
            String query = session.getUri().getQuery();
            if (query != null && query.startsWith("username=")) {
                String username = query.split("=")[1];
                UserSessionService.addSession(username,session);
                System.out.println("WebSocket connection established for user: " + username);
            } else {
                session.close(CloseStatus.BAD_DATA);
            }
        } catch (Exception e) {
            System.err.println("Error during WebSocket connection: " + e.getMessage());
            throw e;
        }
    }


//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        userSessionService.values().removeIf(s -> s.equals(session));
//        System.out.println("sessioin is after connection closed");
//    }
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        userSessions.values().removeIf(s -> s.equals(session));
//        System.out.println("Session closed: " + session.getId() + ", Status: " + status);
//    }

//    public void sendNotificationToUser(String username, String notification) throws Exception {
//        WebSocketSession session = userSessions.get(username);
//        if (session != null && session.isOpen()) {
//            session.sendMessage(new TextMessage(notification));
//            System.out.println("notification is sent to user");
//        }
//    }

    public void sendNotificationToUser(String owenerUsername, String notification) throws Exception {
        WebSocketSession session = UserSessionService.getSession(owenerUsername);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(notification));
            System.out.println("notification is sent to user");
        }
    }
    public void sendNotificationToPolicyUpdate( String notification) throws Exception {
            UserSessionService.getAllSessions().forEach((username, session) -> {
                try {
                    if (session !=null && session.isOpen()) {
                        session.sendMessage(new TextMessage(notification));
                        System.out.println("Notification sent to user: " + username);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
    }

//    public void sendNotificationToTopic(String )



    private String getUsernameFromSession(WebSocketSession session) {
        String uri = session.getUri().toString();
        String[] parts = uri.split("\\?");
        if (parts.length > 1) {
            String[] queryParams = parts[1].split("&");
            for (String param : queryParams) {
                if (param.startsWith("username=")) {
                    return param.split("=")[1];
                }
            }
        }
        return null;
    }

    public void sendMilestoneNotificationToOwner(MilestoneReachedEvent event) {
        try {
            String ownerUserId = event.getOwnerUserId();
            WebSocketSession session= UserSessionService.getSession(ownerUserId);
            String notificationMessage = String.format(
                    "Congratulations! Your post with ID '%s' has reached the milestone '%s' with %d interactions!",
                    event.getPostId(),
                    event.getMilestone(),
                    event.getCurrentCount()
            );
            if(session!=null && session.isOpen()){
                session.sendMessage(new TextMessage(notificationMessage));
            }
//            sendNotificationToUser(ownerUserId, notificationMessage);
        } catch (Exception e) {
            logger.error("Error sending milestone notification: {}", e.getMessage());
        }
    }
}







//package com.app.handler;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;
//
//@Component
//public class NotificationWebSocketHandler extends TextWebSocketHandler {
//
//    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        sessions.add(session);
//    }
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        // Handle incoming WebSocket messages if needed
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        sessions.remove(session);
//    }
//
//    public void broadcast(String notification) throws IOException {
//        for (WebSocketSession session : sessions) {
//            session.sendMessage(new TextMessage(notification));
//        }
//    }
//}
//
