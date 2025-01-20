package com.app.model;


import java.io.Serializable;
import java.time.Instant;

public class NotificationEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    private String event;
    private String photoId;
    private String likedByUsername;
    private String ownerUserName;
    private String photoTitle;
    private Instant timestamp;

    // Getters and Setters
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getLikedByUsername() {
        return likedByUsername;
    }

    public void setLikedByUsername(String likedByUsername) {
        this.likedByUsername = likedByUsername;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setOwnerUserName(String ownerUserName) {
        this.ownerUserName = ownerUserName;
    }

    public String getPhotoTitle() {
        return photoTitle;
    }

    public void setPhotoTitle(String photoTitle) {
        this.photoTitle = photoTitle;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
