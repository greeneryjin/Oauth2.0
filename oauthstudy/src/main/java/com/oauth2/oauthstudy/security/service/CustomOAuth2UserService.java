package com.oauth2.oauthstudy.security.service;

import com.oauth2.oauthstudy.security.model.Account;
import com.oauth2.oauthstudy.security.model.KakaoUser;
import com.oauth2.oauthstudy.security.model.ProviderUser;
import com.oauth2.oauthstudy.security.repository.AccountRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Getter
public class CustomOAuth2UserService extends AbstractOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        //sns clientRegistration 찾기
        ProviderUser providerUser = super.providerUser(clientRegistration, oAuth2User);

        //가입한 유저는 이메일을 가짐
        Account account = accountRepository.findByEmail(providerUser.getEmail());
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        //회원가입
        if (account == null) {
            super.register(providerUser, registrationId);
        } else {
            super.UpdateUser(providerUser);
        }
        return oAuth2User;
    }
}
