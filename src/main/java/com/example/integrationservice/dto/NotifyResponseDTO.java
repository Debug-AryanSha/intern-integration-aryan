package com.example.integrationservice.dto;

import com.example.integrationservice.enums.NotificationStatus;
import com.example.integrationservice.enums.NotificationType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotifyResponseDTO {
    NotificationType notificationType;
    NotificationStatus notificationStatus;
    Long userId;
    String message;
    String title;
}
