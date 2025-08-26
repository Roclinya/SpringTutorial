package com.tutorial.SpringTutorial.Service.Impl;

import com.tutorial.SpringTutorial.entity.Notification;
import com.tutorial.SpringTutorial.repository.NotificationsRepository;
import exception.MyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationStatusUpdateService {

    @Autowired
    private  NotificationsRepository notificationsRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Transactional
    public void updateNotificationStatus(Long id) {
        Notification notification = notificationsRepository.findById(id).get();
        notification.setStatus(Notification.NotificationStatus.SENT);
        throw new MyException("Will it rollback?");
//        Notification notification = new Notification();
//        notification.setStatus(Notification.NotificationStatus.SENT);
//        notification.setMessage("new record");
//        notification.setUuid(UUID.randomUUID().toString());
//        notificationsRepository.save(notification);
    }
}