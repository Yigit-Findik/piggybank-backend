package com.testing.piggybank.Account;

import com.testing.piggybank.account.AccountController;
import com.testing.piggybank.account.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerIntegratieTest {

    @Autowired
    AccountController accountController;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void getAccount() {

    }
}