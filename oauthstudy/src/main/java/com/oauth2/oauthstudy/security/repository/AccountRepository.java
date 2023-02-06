package com.oauth2.oauthstudy.security.repository;

import com.oauth2.oauthstudy.security.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByEmail(String username);

    Account findByRegisterFirst(Boolean first);

    Account findById(String id);
}
