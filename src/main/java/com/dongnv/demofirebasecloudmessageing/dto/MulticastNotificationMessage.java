package com.dongnv.demofirebasecloudmessageing.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MulticastNotificationMessage {
    List<String> recipientTokens;
    String title;
    String body;
    String imageUrl;
    Map<String, String> data;  // dùng cho thông tin bổ sung
}
