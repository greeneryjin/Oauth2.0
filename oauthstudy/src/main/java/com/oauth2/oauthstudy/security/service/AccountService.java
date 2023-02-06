package com.oauth2.oauthstudy.security.service;

import com.oauth2.oauthstudy.security.model.Account;
import com.oauth2.oauthstudy.security.model.ProviderUser;
import com.oauth2.oauthstudy.security.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void register(String registrationId, ProviderUser providerUser) {
        Account account = Account.builder()
                .registrationId(registrationId)
                .provider(providerUser.getProvider())
                .email(providerUser.getEmail())
                .id(providerUser.getId())
                .oAuth2Url(providerUser.getOAuth2Url())
                .registerFirst(true)
                .build();
        accountRepository.save(account);
    }

    @Transactional
    public void UpdateUser(ProviderUser providerUser) {
        Account account = accountRepository.findByEmail(providerUser.getEmail());
        account.updateRegisterFirst(false);
    }
}
