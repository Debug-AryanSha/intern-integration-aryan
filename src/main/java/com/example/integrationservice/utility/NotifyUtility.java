package com.example.integrationservice.utility;

import com.example.integrationservice.dto.NotifyHistoryResponseDTO;
import com.example.integrationservice.dto.NotifyResponseDTO;
import com.example.integrationservice.entity.NotificationHistory;
import org.springframework.stereotype.Component;

@Component
public class NotifyUtility {
    public static NotifyResponseDTO convertNotifyHistoryToResponseDTO(NotificationHistory notificationHistory){
        return NotifyResponseDTO.builder()
                .message(notificationHistory.getMessage())
                .userId(notificationHistory.getUserId())
                .notificationStatus(notificationHistory.getNotificationStatus())
                .notificationType(notificationHistory.getNotificationType())
                .build();
    }

    public static NotifyHistoryResponseDTO convertEntityToHistoryResponseDto(NotificationHistory notificationHistory){
        return NotifyHistoryResponseDTO.builder()
                .notificationType(notificationHistory.getNotificationType())
                .notificationStatus(notificationHistory.getNotificationStatus())
                .message(notificationHistory.getMessage())
                .userId(notificationHistory.getUserId())
                .timestamp(notificationHistory.getTimestamp())
                .build();
    }
}
