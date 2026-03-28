package com.example.integrationservice.repository;

import com.example.integrationservice.entity.NotificationHistory;
import com.example.integrationservice.enums.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface NotificationHistoryRepository extends JpaRepository<NotificationHistory, UUID> {
    @Query(value = """
            select * from notification_history nh where nh.status = COALESCE(:status,nh.status)
            """, nativeQuery = true)
    NotificationHistory fetchHistoryByStatus(NotificationStatus status);
}
