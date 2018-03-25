package com.watchovr.api.services;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import java.util.List;

@Service
public class DiscordService implements EventListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private Environment environment;

    private JDA jda;

    public DiscordService() {
    }

    @PostConstruct
    private void postConstruct() throws LoginException, InterruptedException {
        this.jda = new JDABuilder(AccountType.BOT)
                .setToken(this.environment.getProperty("DISCORD_BOT_TOKEN"))
                .addEventListener(this)
                .buildBlocking();
        this.logger.debug("Discord API Connected");
    }

    public void postMessage(String text, String channelName) {
        List<TextChannel> textChannelList = this.jda.getTextChannels();
        for(TextChannel textChannel : textChannelList) {
            if(textChannel.getName().equalsIgnoreCase("general")) {
                textChannel.sendMessage(text).complete();
            }
        }
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof ReadyEvent) {
        }

        if(event instanceof MessageReceivedEvent) {
            MessageReceivedEvent messageReceivedEvent = (MessageReceivedEvent) event;
        }
    }
}
