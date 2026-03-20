package com.example.integrationservice.controller;

import com.example.integrationservice.dto.NotifyHealthResponseDTO;
import com.example.integrationservice.dto.NotifyHistoryResponseDTO;
import com.example.integrationservice.dto.NotifyRequestDTO;
import com.example.integrationservice.dto.NotifyStatisticsResponseDTO;
import com.example.integrationservice.enums.NotificationStatus;
import com.example.integrationservice.service.NotificationIntegrationService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notify")
public class NotificationIntegrationController {

    private final NotificationIntegrationService notificationIntegrationService;

    @PostMapping("/sms")
    public void sendSms(@RequestBody NotifyRequestDTO notifyRequest) {
        notificationIntegrationService.notifySms(notifyRequest);
    }

    @PostMapping("/push")
    public void sendPush(@RequestBody NotifyRequestDTO notifyRequest) {
        notificationIntegrationService.notifyPush(notifyRequest);
    }

    @GetMapping("/history")
    public ResponseEntity<NotifyHistoryResponseDTO> fetchHistory(@RequestParam @Nullable NotificationStatus status) {
        return ResponseEntity.ok(notificationIntegrationService.fetchHistory(status));
    }

    @GetMapping("/stats")
    public ResponseEntity<NotifyStatisticsResponseDTO> fetchStatistics() {
        return ResponseEntity.ok(notificationIntegrationService.fetchStatistics());
    }

    @GetMapping("/health")
    public ResponseEntity<NotifyHealthResponseDTO> getServiceHealth(@RequestBody NotifyRequestDTO notifyRequest) {
        return ResponseEntity.ok(notificationIntegrationService.getServiceHealth(notifyRequest));
    }

}
