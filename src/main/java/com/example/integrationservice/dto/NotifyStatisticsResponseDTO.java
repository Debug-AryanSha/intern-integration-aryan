package com.example.integrationservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotifyStatisticsResponseDTO {
    Long totalSent;
    Long totalFailed;
    double failureRate;
    Long totalSmsSent;
    Long totalSmsFailed;
    Long totalPushSent;
    Long totalPushFailed;
}
