package com.tutorial.SpringTutorial.Controller;

import com.tutorial.SpringTutorial.Config.PropertiesConfig;
import com.tutorial.SpringTutorial.Service.Impl.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class NotificationController {

  @Autowired
  private NotificationsService notificationsService;



    @PostMapping("/sendNotification")
    public void getInviteEndDate(@RequestParam Long id) {
        notificationsService.sendNotification(id);
    }
}
