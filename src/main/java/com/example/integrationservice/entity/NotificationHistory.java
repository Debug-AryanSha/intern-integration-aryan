package com.example.integrationservice.entity;

import com.example.integrationservice.enums.NotificationStatus;
import com.example.integrationservice.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "notification_history")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid")
    @JdbcType(CharJdbcType.class)
    UUID notificationUUID;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    NotificationType notificationType;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    NotificationStatus notificationStatus;

    @Column(name = "user_id")
    Long userId;

    String title;

    String message;

    LocalDateTime timestamp;

    String reason;
}
