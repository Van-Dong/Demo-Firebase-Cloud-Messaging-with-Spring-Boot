package com.dongnv.demofirebasecloudmessageing.service;

import com.dongnv.demofirebasecloudmessageing.dto.MulticastNotificationMessage;
import com.dongnv.demofirebasecloudmessageing.dto.NotificationMessage;
import com.dongnv.demofirebasecloudmessageing.dto.SubscribeTopicRequest;
import com.dongnv.demofirebasecloudmessageing.dto.TopicMessage;
import com.google.firebase.messaging.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FirebaseMessageService {
    FirebaseMessaging firebaseMessaging;

    // Send to only 1 device by 1 token
    public String sendNotificationByToken(NotificationMessage notificationMessage) {

        Notification notification = Notification.builder()
                .setTitle(notificationMessage.getTitle())
                .setBody(notificationMessage.getBody())
                .setImage(notificationMessage.getImageUrl())
                .build();

        Message message = Message.builder()
                .setToken(notificationMessage.getRecipientToken())
                .setNotification(notification)
                .putAllData(notificationMessage.getData()) // thông tin bổ sung (không hiển thị trên UI)
                .build();

        try {
            firebaseMessaging.send(message);  // Hàm này sẽ return messageId
            return "Success Sending Notification";
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return "Error Sending Notification";
        }
    }


    // Send Multi device by multi-token
    public String sendNotificationByMultiToken(MulticastNotificationMessage message) {
        MulticastMessage msg = MulticastMessage.builder()
                .addAllTokens(message.getRecipientTokens())
                .setNotification(Notification.builder()
                        .setTitle(message.getTitle())
                        .setBody(message.getBody())
                        .setImage(message.getImageUrl())
                        .build())
                .putAllData(message.getData())
                .build();

        try {
            firebaseMessaging.sendEachForMulticast(msg); // return BatchResponse, chứa messageId thành công và các error
            return "Success Sending Notification";
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return "Error Sending Notification";
        }
    }

    public String subscribeToTopic(SubscribeTopicRequest subscribeTopicRequest) {
        try {
            firebaseMessaging.subscribeToTopic(subscribeTopicRequest.getTokens(),
                    subscribeTopicRequest.getTopicName());
            return "Subscribe success";
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return "Subscribe failed";
        }
    }

    public String sendNotificationByTopic(TopicMessage topicMessage) {
        Message message = Message.builder()
                .setTopic(topicMessage.getTopic())
                .setNotification(Notification.builder()
                        .setTitle(topicMessage.getTitle())
                        .setBody(topicMessage.getBody())
                        .setImage(topicMessage.getImageUrl())
                        .build())
                .putAllData(topicMessage.getData()) // thông tin bổ sung (không hiển thị trên UI)
                .build();

        try {
            firebaseMessaging.send(message);
            return "Success Sending Notification";
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return "Error Sending Notification";
        }
    }

}
