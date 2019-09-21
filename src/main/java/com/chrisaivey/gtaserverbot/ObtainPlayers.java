package com.chrisaivey.gtaserverbot;


import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.entities.Message;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class ObtainPlayers {


    public void obtainPlayers(String playersUrl, Message message) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(playersUrl);
        HttpResponse response;

        {
            try {
                response = client.execute(request);
                ObjectMapper objectMapper = new ObjectMapper();
                Players[] players = objectMapper.readValue(response.getEntity().getContent(), Players[].class);

                message.getChannel().sendMessage("Total players: " + players.length).complete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
