package com.tutorial.SpringTutorial.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@Table(name = "notification",schema = "GSMUSER")
@Table(name = "notification")
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTIFICATION_SEQ_GENERATOR")
    @SequenceGenerator(name = "NOTIFICATION_SEQ_GENERATOR", sequenceName = "TRAINING_SEQ")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    @Column(nullable = false)
    private String message;

    @Column(length = 36, nullable = false, updatable = false)
    private String uuid = UUID.randomUUID().toString();

    public Notification(String message) {
        this.status = NotificationStatus.NEW;
        this.message = message;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public boolean equals(Object that) {
        return this == that || that instanceof Notification
                && Objects.equals(uuid, ((Notification) that).uuid);
    }

    public enum NotificationStatus {
        NEW, SENT
    }

}