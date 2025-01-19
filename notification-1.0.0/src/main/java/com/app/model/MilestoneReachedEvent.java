package com.app.model;

import java.io.Serializable;
import java.time.Instant;

public class MilestoneReachedEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private String event;          // Type of event (e.g., "milestone_reached")
    private String postId;         // Unique ID of the post
    private String ownerUserId;    // User ID of the post owner
    private String milestone;      // Milestone achieved (e.g., "100_likes")
    private int currentCount;      // Current count related to the milestone
    private Instant timestamp;     // Timestamp of the event

    // Constructors
    public MilestoneReachedEvent() {
    }

    public MilestoneReachedEvent(String event, String postId, String ownerUserId, String milestone, int currentCount, Instant timestamp) {
        this.event = event;
        this.postId = postId;
        this.ownerUserId = ownerUserId;
        this.milestone = milestone;
        this.currentCount = currentCount;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "MilestoneReachedEvent{" +
                "event='" + event + '\'' +
                ", postId='" + postId + '\'' +
                ", ownerUserId='" + ownerUserId + '\'' +
                ", milestone='" + milestone + '\'' +
                ", currentCount=" + currentCount +
                ", timestamp=" + timestamp +
                '}';
    }
}
