    package com.app.service;

    import com.app.model.MilestoneReachedEvent;
    import com.app.model.NotificationEvent;
    import com.app.model.PolicyUpdateEvent;
    import com.fasterxml.jackson.core.JsonProcessingException;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import org.springframework.amqp.core.AmqpTemplate;
    import org.springframework.amqp.rabbit.core.RabbitTemplate;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    @Service
    public class NotificationProducer {

        private final RabbitTemplate rabbitTemplate;
        private final ObjectMapper objectMapper;

        @Autowired
        private AmqpTemplate amqpTemplate;

        public NotificationProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
            this.rabbitTemplate = rabbitTemplate;
            this.objectMapper = objectMapper;
        }

        public void sendPhotoLikedNotification(NotificationEvent event, String eventName, String routingKey) throws JsonProcessingException {
            String json = objectMapper.writeValueAsString(event);
             routingKey = "photo_liked." + event.getPhotoId();
            rabbitTemplate.convertAndSend("notification.direct.exchange", routingKey,json);
        }


            // Milestone reached event
    public void sendMilestoneNotification(String milestone, String message) {
        String routingKey = "milestone_reached." + milestone;
        rabbitTemplate.convertAndSend("notification.topic.exchange", routingKey, message);
    }

    // Policy update event
    public void sendPolicyUpdateNotification(PolicyUpdateEvent policyUpdateEvent) {
        rabbitTemplate.convertAndSend("notification.fanout.exchange", "", policyUpdateEvent);
    }


        public void sendMilestoneNotification(MilestoneReachedEvent event, String routingKey) {
            rabbitTemplate.convertAndSend("notification.topic.exchange", routingKey, event);
        }

    }








//1.



//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class NotificationProducer {
//
//    private final RabbitTemplate rabbitTemplate;
//
//    public NotificationProducer(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    // Photo liked event
//    public void sendPhotoLikedNotification(String photoId, String message) {
//        String routingKey = "photo_liked." + photoId;
//        rabbitTemplate.convertAndSend("notification.direct.exchange", routingKey, message);
//    }
//
//    // Milestone reached event
//    public void sendMilestoneNotification(String milestone, String message) {
//        String routingKey = "milestone_reached." + milestone;
//        rabbitTemplate.convertAndSend("notification.topic.exchange", routingKey, message);
//    }
//
//    // Policy update event
//    public void sendPolicyUpdateNotification(String message) {
//        rabbitTemplate.convertAndSend("notification.fanout.exchange", "", message);
//    }
//}
//
