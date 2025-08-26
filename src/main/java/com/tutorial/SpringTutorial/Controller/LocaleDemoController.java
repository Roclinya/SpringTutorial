package com.tutorial.SpringTutorial.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
@RequestMapping("/api")
@RestController
public class LocaleDemoController {

        @Autowired
        private MessageSource messageSource;
//        @Autowired
//        private UserDao userDao;

        @GetMapping("/localeDemo/{userId}")
        @ResponseStatus(HttpStatus.OK)
        public String getOneUser(@PathVariable("userId") Long userId) throws Exception {
//            Optional<User> userOption = userDao.findById(userId);
//            if (userOption.isEmpty()) {
                String msg = messageSource.getMessage(
                        "current.locale",
                        new String[]{userId.toString()},
                        Locale.TAIWAN);
//                log.error(msg);
//            }
//            return userOption.get();
                return msg;
        }
    }
