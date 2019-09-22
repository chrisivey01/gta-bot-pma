package com.chrisaivey.gtaserverbot;


import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ObtainPlayers {


    public void obtainPlayers(String playersUrl, Message message) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(playersUrl);
        HttpResponse response;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        {
            try {
                response = client.execute(request);
                ObjectMapper objectMapper = new ObjectMapper();
                Players[] players = objectMapper.readValue(response.getEntity().getContent(), Players[].class);
                EmbedBuilder eb = new EmbedBuilder();
                int i = 0;
                while(i < players.length)
                {
                        String playerNames = "Player Name: " + players[i].name + "\n" + "ID: " + players[i].id + "\n"+ "Ping: "+ players[i].ping;
                        String playerIdentifiers = "Player Identifiers" + "\n" + "-----------------------------------------------------------------" + "\n" + players[i].identifiers.toString().replace("[", "").replace("]", "").replace(",", "\n") +"\n" + "-----------------------------------------------------------------";
                        eb.setTitle("Players Online", null);
                        eb.addField(playerNames, playerIdentifiers , false);
                        eb.setColor(255);
                        eb.setFooter("Total Players: " + players.length + "\n"+ formatter.format(date), null);
                    i++;
                }
                    message.getChannel().sendMessage(eb.build()).complete();



            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
