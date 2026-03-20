package com.example.integrationservice.service;

import com.example.integrationservice.dto.*;
import com.example.integrationservice.enums.NotificationStatus;

public interface NotificationIntegrationService {
    void notifySms(NotifyRequestDTO notifyRequest);

    void notifyPush(NotifyRequestDTO notifyRequest);

    NotifyHistoryResponseDTO fetchHistory(NotificationStatus status);

    NotifyStatisticsResponseDTO fetchStatistics();

    NotifyHealthResponseDTO getServiceHealth(NotifyRequestDTO notifyRequest);
}
