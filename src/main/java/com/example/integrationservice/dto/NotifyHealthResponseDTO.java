package com.example.integrationservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotifyHealthResponseDTO {
    String serviceName;
    String status;
    String serverUpTime;
}
