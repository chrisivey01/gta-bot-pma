package com.chrisaivey.gtaserverbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public class Bot {
    public Bot(@Value("${token}") String token) {
        try {
            JDA jda = new JDABuilder(token)
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .addEventListeners(new Chat())
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
