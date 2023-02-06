package com.oauth2.oauthstudy.security.service;

import com.oauth2.oauthstudy.security.model.Account;
import com.oauth2.oauthstudy.security.model.KakaoUser;
import com.oauth2.oauthstudy.security.model.NaverUser;
import com.oauth2.oauthstudy.security.model.ProviderUser;
import com.oauth2.oauthstudy.security.repository.AccountRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
@Getter
public abstract class AbstractOAuth2UserService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    public void register(ProviderUser providerUser, String registrationId) {
        accountService.register(registrationId, providerUser);
    }

    public ProviderUser providerUser(ClientRegistration clientRegistration, OAuth2User oAuth2User) {
        String registrationId = clientRegistration.getRegistrationId().toLowerCase(Locale.ROOT);
        if(registrationId.equals("kakao")) {
            return new KakaoUser(oAuth2User, clientRegistration);
        } else if(registrationId.equals("naver")) {
            return new NaverUser(oAuth2User, clientRegistration);
        }
        return null;
    }

    public void UpdateUser(ProviderUser providerUser) {
        accountService.UpdateUser(providerUser);
    };
}
