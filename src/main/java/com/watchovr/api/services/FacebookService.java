package com.watchovr.api.services;

import facebook4j.*;
import facebook4j.auth.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class FacebookService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Facebook facebook;

    @PostConstruct
    private void postConstruct() {
        this.facebook = new FacebookFactory().getInstance();
    }

    /**
     * Post a text message to a Group.
     * @param text
     * @param groupId
     */
    public void postToGroup(String text, String groupId) {
        try {
            Group group = this.facebook.groups().getGroup(groupId);
            PostUpdate postUpdate = new PagePostUpdate(text);

            this.facebook.groups().postGroupFeed(groupId, postUpdate);
        } catch (FacebookException fe) {
            this.logger.error(fe.getErrorMessage());
        }
    }

    /**
     * Get a Group's post by Group ID.
     * @param groupId
     */
    public void getGroupPosts(String groupId) {
        try {
            Group group = this.facebook.groups().getGroup(groupId);
            ResponseList<Post> groupPosts = this.facebook.groups().getGroupFeed(groupId);
            for(Post post : groupPosts) {
                post.getCreatedTime();
            }
        } catch (FacebookException fe) {
            this.logger.error(fe.getErrorMessage());
        }
    }
}
