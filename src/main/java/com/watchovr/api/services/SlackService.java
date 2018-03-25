package com.watchovr.api.services;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;

@Service
public class SlackService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private Environment environment;

    private SlackSession session;

    public SlackService() {
    }

    @PostConstruct
    private void postConstruct() {
        try {
            this.session = SlackSessionFactory.createWebSocketSlackSession(this.environment.getProperty("SLACK_BOT_TOKEN"));
            this.session.connect();
            this.logger.debug("Slack API Connected");
        } catch (IOException ioe) {
            this.logger.error(ioe.getMessage());
        }
    }

    public void sendMessage() {
        SlackChannel channel = this.session.findChannelByName("general");
        this.session.sendMessage(channel, "hi im a bot" );
    }
}
