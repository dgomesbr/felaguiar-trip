package com.diegomagalhaes.felaguiar.trip.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.instagram.api.Instagram;
import org.springframework.social.instagram.connect.InstagramConnectionFactory;
import org.springframework.social.instagram.connect.SimpleSignInAdapter;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import javax.sql.DataSource;

@EnableSocial
public class SocialConfig implements SocialConfigurer {
    @Autowired
    private DataSource dataSource;

    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        return new ConnectionFactoryRegistry();
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cf, Environment env) {
        cf.addConnectionFactory(new InstagramConnectionFactory(env.getProperty("client.id"),env.getProperty("client.secret")));
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(
                dataSource,
                connectionFactoryLocator,
                Encryptors.noOpText()
        );
    }

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }

    @Bean
    public SignInAdapter signInAdapter() {
        return new SimpleSignInAdapter();
    }

    @Bean
    @Scope(value="request", proxyMode= ScopedProxyMode.INTERFACES)
    public Instagram instagram(ConnectionRepository repository) {
        Connection<Instagram> connection = repository.findPrimaryConnection(Instagram.class);
        return connection != null ? connection.getApi() : null;
    }

    @Bean
    public ProviderSignInController providerSignInController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository) {
        return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new SimpleSignInAdapter());
    }
}
