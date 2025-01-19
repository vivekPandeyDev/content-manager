package com.app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;

public class UserSessionService {

    private final static ConcurrentHashMap<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();

    public static void addSession(String username, WebSocketSession session) {
        userSessions.put(username, session);
    }

    public static WebSocketSession getSession(String username) {
        return userSessions.get(username);
    }

    public static void removeSession(String username) {
        userSessions.remove(username);
    }

    public static ConcurrentHashMap<String, WebSocketSession> getAllSessions(){
        return userSessions;
    }
}