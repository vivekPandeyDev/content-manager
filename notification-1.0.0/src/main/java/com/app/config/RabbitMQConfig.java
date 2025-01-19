package com.app.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("notification.direct.exchange");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("notification.fanout.exchange");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("notification.topic.exchange");
    }

    @Bean
    public Queue photoLikedQueue() {
        return new Queue("photo_liked_queue", true);
    }

    @Bean
    public Queue policyUpdateQueue() {
        return new Queue("policy_update_queue", true);
    }

    @Bean
    public Queue achievementNotificationQueue() {
        return new Queue("achievement_notification_queue", true);
    }

    @Bean
    public Queue analyticsQueue() {
        return new Queue("analytics_queue", true);
    }

    @Bean
    public Queue leaderboardQueue() {
        return new Queue("leaderboard_queue", true);
    }

    @Bean
    public Binding bindPhotoLikedQueue(DirectExchange directExchange, Queue photoLikedQueue) {
        return BindingBuilder.bind(photoLikedQueue).to(directExchange).with("photo_liked.#");
    }

    @Bean
    public Binding bindPolicyUpdateQueue(FanoutExchange fanoutExchange, Queue policyUpdateQueue) {
        return BindingBuilder.bind(policyUpdateQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bindAchievementNotificationQueue(TopicExchange topicExchange, Queue achievementNotificationQueue) {
        return BindingBuilder.bind(achievementNotificationQueue).to(topicExchange).with("milestone_reached.achievement.*");
    }

    @Bean
    public Binding bindAnalyticsQueue(TopicExchange topicExchange, Queue analyticsQueue) {
        return BindingBuilder.bind(analyticsQueue).to(topicExchange).with("milestone_reached.analytics.*");
    }

    @Bean
    public Binding bindLeaderboardQueue(TopicExchange topicExchange, Queue leaderboardQueue) {
        return BindingBuilder.bind(leaderboardQueue).to(topicExchange).with("milestone_reached.leaderboard.*");
    }
}






