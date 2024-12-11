package com.dongnv.demofirebasecloudmessageing.controller;

import com.dongnv.demofirebasecloudmessageing.dto.MulticastNotificationMessage;
import com.dongnv.demofirebasecloudmessageing.dto.NotificationMessage;
import com.dongnv.demofirebasecloudmessageing.dto.SubscribeTopicRequest;
import com.dongnv.demofirebasecloudmessageing.dto.TopicMessage;
import com.dongnv.demofirebasecloudmessageing.service.FirebaseMessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class NotificationController {
    FirebaseMessageService firebaseMessageService;

    @PostMapping
    public String sendNotificationByToken(@RequestBody NotificationMessage notificationMessage) {
        return firebaseMessageService.sendNotificationByToken(notificationMessage);
    }

    @PostMapping("/multi-token")
    public String sendMultiNotificationByTokens(@RequestBody MulticastNotificationMessage notificationMessage) {
        return firebaseMessageService.sendNotificationByMultiToken(notificationMessage);
    }

    @PostMapping("/subscribe-topic")
    public String subscribeTopic(@RequestBody SubscribeTopicRequest subscribeTopicRequest) {
        return firebaseMessageService.subscribeToTopic(subscribeTopicRequest);
    }

    @PostMapping("/topic")
    public String sendNotificationByTopic(@RequestBody TopicMessage topicMessage) {
        return firebaseMessageService.sendNotificationByTopic(topicMessage);
    }
}
