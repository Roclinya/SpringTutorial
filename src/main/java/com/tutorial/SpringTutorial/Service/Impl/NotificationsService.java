package com.tutorial.SpringTutorial.Service.Impl;

import com.tutorial.SpringTutorial.entity.Notification;
import com.tutorial.SpringTutorial.repository.NotificationsRepository;
import exception.MyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationsService {

    private final NotificationsRepository notificationsRepository;
    private final NotificationStatusUpdateService notificationStatusUpdateService;
    //由 @RequiredArgsConstructor 取代
//    public NotificationsService(NotificationsRepository notificationsRepository, NotificationStatusUpdateService notificationStatusUpdateService) {
//        this.notificationsRepository = notificationsRepository;
//        this.notificationStatusUpdateService = notificationStatusUpdateService;
//    }

    //https://medium.com/@aleksanderkolata/use-case-02-spring-transactional-requires-new-propagation-mode-cb7c16e1dd16
//    @Transactional(noRollbackFor= MyException.class)
//    @Transactional
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendNotification(Long id) {
        try {
            notificationStatusUpdateService.updateNotificationStatus(id);
        } catch (Exception e) {
//            throw new RuntimeException(e);
            System.out.println("Inner transaction has thrown an exception");
        }

        Notification notification = notificationsRepository.findById(id).get();
        notification.setMessage("UPDATED MESSAGE");
//        throw new MyException("Will Outer rollback?");
    }
}
