package com.oauth2.oauthstudy.security.model;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class KakaoUser extends OAuth2ProviderUser {

    public KakaoUser(OAuth2User oAuth2User, ClientRegistration clientRegistration) {
        super(oAuth2User.getAttributes(), oAuth2User, clientRegistration);
    }

    @Override
    public String getId() {
        return String.valueOf(getAttributes().get("id"));
    }

    @Override
    public String getEmail() {
        Map<String, Object> kakao_account = (Map<String, Object>) getAttributes().get("kakao_account");
        return String.valueOf(kakao_account.get("email"));
    }

    @Override
    public String getOAuth2Url() {
        Map<String, Object> kakao_account = (Map<String, Object>) getAttributes().get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");

        if (profile == null) {
            return null;
        }
        return String.valueOf(profile.get("thumbnail_image_url"));
    }
}
