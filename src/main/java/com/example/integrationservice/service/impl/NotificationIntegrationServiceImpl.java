package com.example.integrationservice.service.impl;

import com.example.integrationservice.dto.*;
import com.example.integrationservice.entity.NotificationHistory;
import com.example.integrationservice.enums.NotificationStatus;
import com.example.integrationservice.enums.NotificationType;
import com.example.integrationservice.repository.NotificationHistoryRepository;
import com.example.integrationservice.service.NotificationIntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static com.example.integrationservice.utility.NotifyUtility.convertEntityToHistoryResponseDto;
import static com.example.integrationservice.utility.NotifyUtility.convertNotifyHistoryToResponseDTO;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationIntegrationServiceImpl implements NotificationIntegrationService {

    private final NotificationHistoryRepository notificationHistoryRepository;

    @Override
    @Async
    public void notifySms(NotifyRequestDTO notifyRequest) {
        NotifyResponseDTO notifyResponse = new NotifyResponseDTO();
        log.info("SMS notification received : {}", notifyRequest);
        try {
            Thread.sleep(200);

            boolean failed = new Random().nextInt(100) < 10;
            NotificationStatus status = failed ? NotificationStatus.FAILED : NotificationStatus.SENT;

            NotificationHistory notificationHistory = NotificationHistory.builder()
                    .notificationType(NotificationType.SMS)
                    .notificationStatus(status)
                    .userId(notifyRequest.getReceiverUserId())
                    .message(notifyRequest.getMessage())
                    .timestamp(LocalDateTime.now())
                    .build();
            notifyResponse = convertNotifyHistoryToResponseDTO(notificationHistoryRepository.save(notificationHistory));
        } catch (Exception e) {
            log.error("SMS notification interrupted : {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        log.info("SMS notification sent successfully : {}", notifyResponse);
    }

    @Override
    @Async
    public void notifyPush(NotifyRequestDTO notifyRequest) {
        NotifyResponseDTO notifyResponse = new NotifyResponseDTO();
        log.info("PUSH notification received : {}", notifyRequest);
        try {
            Thread.sleep(200);

            boolean failed = new Random().nextInt(100) < 20;
            NotificationStatus status = failed ? NotificationStatus.FAILED : NotificationStatus.SENT;

            NotificationHistory notificationHistory = NotificationHistory.builder()
                    .notificationType(NotificationType.PUSH)
                    .notificationStatus(status)
                    .userId(notifyRequest.getReceiverUserId())
                    .title(notifyRequest.getTitle())
                    .message(notifyRequest.getMessage())
                    .timestamp(LocalDateTime.now())
                    .build();
            notifyResponse = convertNotifyHistoryToResponseDTO(notificationHistoryRepository.save(notificationHistory));
        } catch (Exception e) {
            log.error("PUSH notification interrupted : {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        log.info("PUSH notification sent successfully : {}", notifyResponse);
    }

    @Override
    public NotifyHistoryResponseDTO fetchHistory(NotificationStatus status) {
        return convertEntityToHistoryResponseDto(notificationHistoryRepository.fetchHistoryByStatus(status));
    }

    @Override
    public NotifyStatisticsResponseDTO fetchStatistics() {
        List<NotificationHistory> allNotifications = notificationHistoryRepository.findAll();

        List<NotificationHistory> totalSent = allNotifications
                .stream()
                .filter(notify -> NotificationStatus.SENT.equals(notify.getNotificationStatus()))
                .toList();

        List<NotificationHistory> totalFailed = allNotifications
                .stream()
                .filter(notify -> NotificationStatus.FAILED.equals(notify.getNotificationStatus()))
                .toList();

        double failureRate = ((double) totalFailed.size() / allNotifications.size()) * 100;

        return NotifyStatisticsResponseDTO.builder()
                .totalSent((long) totalSent.size())
                .totalFailed((long) totalFailed.size())
                .failureRate(failureRate)
                .totalSmsSent(totalSent.stream()
                        .filter(notify -> NotificationType.SMS.equals(notify.getNotificationType()))
                        .count()
                )
                .totalSmsFailed(totalFailed.stream()
                        .filter(notify -> NotificationType.SMS.equals(notify.getNotificationType()))
                        .count())
                .totalPushSent(totalSent.stream()
                        .filter(notify -> NotificationType.PUSH.equals(notify.getNotificationType()))
                        .count())
                .totalPushFailed(totalFailed.stream()
                        .filter(notify -> NotificationType.PUSH.equals(notify.getNotificationType()))
                        .count())
                .build();
    }

    @Override
    public NotifyHealthResponseDTO getServiceHealth(NotifyRequestDTO notifyRequest) {
        return null;
    }
}
