package com.dongnv.demofirebasecloudmessageing.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscribeTopicRequest {
    List<String> tokens = new ArrayList<>();
    String topicName;
}
