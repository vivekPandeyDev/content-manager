package com.app.model;


import java.time.LocalDateTime;

public class PolicyUpdateEvent {

    private static final long serialVersionUID = 1L;
    private String event;          // Event type (e.g., "policy_update")
    private String policyType;     // Type of policy updated (e.g., "terms_of_service")
    private String policyVersion;  // Version of the updated policy
    private LocalDateTime effectiveDate; // Effective date of the updated policy
    private LocalDateTime timestamp;     // Timestamp of the event

    // Constructors
    public PolicyUpdateEvent() {
    }

    public PolicyUpdateEvent(String event, String policyType, String policyVersion, LocalDateTime effectiveDate, LocalDateTime timestamp) {
        this.event = event;
        this.policyType = policyType;
        this.policyVersion = policyVersion;
        this.effectiveDate = effectiveDate;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getPolicyVersion() {
        return policyVersion;
    }

    public void setPolicyVersion(String policyVersion) {
        this.policyVersion = policyVersion;
    }

    public LocalDateTime getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "PolicyUpdateEvent{" +
                "event='" + event + '\'' +
                ", policyType='" + policyType + '\'' +
                ", policyVersion='" + policyVersion + '\'' +
                ", effectiveDate=" + effectiveDate +
                ", timestamp=" + timestamp +
                '}';
    }
}
