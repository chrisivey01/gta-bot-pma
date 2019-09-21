package com.chrisaivey.gtaserverbot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Chat extends ListenerAdapter {


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        ObtainPlayers obtain = new ObtainPlayers();

        Message message = event.getMessage();
        String content = message.getContentDisplay();

        String playersUrl = "http://74.91.119.91:30120/players.json";


        if(content.startsWith("!online")){

            obtain.obtainPlayers(playersUrl, message);

        }

    }
}
