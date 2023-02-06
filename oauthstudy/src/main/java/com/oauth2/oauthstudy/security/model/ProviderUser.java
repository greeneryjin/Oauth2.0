package com.oauth2.oauthstudy.security.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Map;

public interface ProviderUser {

    String getId();
    String getOAuth2Url();
    String getEmail();
    String getProvider();
    List<? extends GrantedAuthority> getAuthorities(); //사용자 권한
    Map<String, Object> getAttributes(); //사용자 속성
}
