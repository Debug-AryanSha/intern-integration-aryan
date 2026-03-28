package com.example.integrationservice.dto;

import com.example.integrationservice.enums.NotificationStatus;
import com.example.integrationservice.enums.NotificationType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotifyHistoryResponseDTO {
    UUID notificationUUID;
    NotificationType notificationType;
    NotificationStatus notificationStatus;
    Long userId;
    String title;
    String message;
    LocalDateTime timestamp;
}
