package com.oauth2.oauthstudy.security;

import com.oauth2.oauthstudy.security.model.Account;
import com.oauth2.oauthstudy.security.repository.AccountRepository;
import org.graalvm.compiler.lir.CompositeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@CompositeValue.Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = true)
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        OAuth2User auth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = auth2User.getAttributes();
        String registrationId = String.valueOf(attributes.get("id"));
        String targetUrl = determineTargetUrl(registrationId);
        response.sendRedirect(targetUrl);
    }

    //token을 생성하고 이를 포함한 프론트엔드로의 uri를 생성한다.
    protected String determineTargetUrl (String id) throws IOException {
        Account account = accountRepository.findById(id);
        if (account.getRegisterFirst()) {
            return UriComponentsBuilder.fromUriString("http://localhost:8080/api/user" + account.getId()).build().toUriString();
        }else {
            return UriComponentsBuilder.fromUriString("http://localhost:8080").build().toUriString();
        }
    }
}
