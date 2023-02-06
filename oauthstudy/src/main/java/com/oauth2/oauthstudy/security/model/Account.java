package com.oauth2.oauthstudy.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id", nullable = false)
    private Long accountId;
    private String registrationId;
    private String id;
    private String oAuth2Url;
    private String password;
    private String provider;
    private String email;
    private Boolean registerFirst;

    public Account() {

    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updateRegisterFirst(boolean b) {
        this.registerFirst = b;
    }
}
