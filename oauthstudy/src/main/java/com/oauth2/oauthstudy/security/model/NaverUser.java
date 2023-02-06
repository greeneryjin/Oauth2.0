package com.oauth2.oauthstudy.security.model;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class NaverUser extends OAuth2ProviderUser {

    public NaverUser(OAuth2User oAuth2User, ClientRegistration clientRegistration) {
        super((Map<String, Object>) oAuth2User.getAttributes().get("response"), oAuth2User, clientRegistration);
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getOAuth2Url() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }
}
