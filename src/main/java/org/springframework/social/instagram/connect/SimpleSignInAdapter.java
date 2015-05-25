package org.springframework.social.instagram.connect;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Created by diego.magalhaes on 25/05/2015.
 */
public class SimpleSignInAdapter  implements SignInAdapter {

    public SimpleSignInAdapter() {
    }

    @Override
    public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, null, null));
        return null;
    }




}
