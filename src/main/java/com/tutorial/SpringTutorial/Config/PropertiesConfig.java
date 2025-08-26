package com.tutorial.SpringTutorial.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesConfig {


    @Value("${inviteEndDate}")
    private int inviteEndDate;

    public int getInviteEndDate() {
        return inviteEndDate;
    }
}
