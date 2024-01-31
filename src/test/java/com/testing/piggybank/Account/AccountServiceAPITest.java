package com.testing.piggybank.Account;

import com.testing.piggybank.account.GetAccountsResponse;
import com.testing.piggybank.transaction.GetTransactionsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.testing.piggybank.model.Account;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountServiceAPITest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getAllAccountsTest() {
        // Arrange
        final List<Account> accounts = List.of(
                new Account(),
                new Account()
        );

        // Act
        final ResponseEntity<GetAccountsResponse> response = testRestTemplate.getForEntity("/api/v1/accounts", GetAccountsResponse.class);

        // Assertion
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().getAccounts().size());
    }
}
