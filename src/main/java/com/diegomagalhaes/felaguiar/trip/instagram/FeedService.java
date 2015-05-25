package com.diegomagalhaes.felaguiar.trip.instagram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.social.instagram.api.PagedMediaList;
import org.springframework.social.instagram.api.impl.InstagramTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by diego.magalhaes on 25/05/2015.
 */
@Component
public class FeedService {
    @Autowired
    private Environment env;

    public PagedMediaList getMediasByTag(){
        return instagramTemplate().tagOperations().getRecentMedia(env.getProperty("client.tagname"));
    }

    @Bean
    public InstagramTemplate instagramTemplate() {
        return new InstagramTemplate(env.getProperty("client.id"), env.getProperty("client.token"));
    }
}
