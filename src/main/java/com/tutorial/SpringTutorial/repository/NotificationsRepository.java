package com.tutorial.SpringTutorial.repository;

import com.tutorial.SpringTutorial.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationsRepository extends JpaRepository<Notification, Long> {
}