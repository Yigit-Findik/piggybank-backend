package com.testing.piggybank.Account;

import com.testing.piggybank.account.AccountRepository;
import com.testing.piggybank.account.AccountService;
import com.testing.piggybank.model.Account;
import com.testing.piggybank.model.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AccountServiceUnitTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void test() {
        // ARrange
        Account account = new Account();
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        // Act
        Optional<Account> result = accountService.getAccount(1L);

        // Assertion
        Assertions.assertEquals(Optional.of(account), result);
        Mockito.verify(accountRepository).findById(1L);
    }

    @Test
    public void testUpdateBalance() {
        // ARrange
        Account account = new Account();
        account.setId(1L);
        account.setBalance(BigDecimal.TEN);

        Mockito.when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));

        // Act
        accountService.updateBalance(1L, BigDecimal.valueOf(5), Direction.DEBIT);

        // Assertion
        Assertions.assertEquals(BigDecimal.valueOf(15), account.getBalance());
        Mockito.verify(accountRepository).save(account);
    }

    @Test
    public void testGetAccountsByUserId() {
        // ARrange
        List<Account> accounts = new ArrayList<>();
        Account account1 = new Account();
        account1.setId(1L);
        account1.setUserId(1L);
        accounts.add(account1);

        Mockito.when(accountRepository.findAllByUserId(1L)).thenReturn(accounts);

        // Act
        List<Account> result = accountService.getAccountsByUserId(1L);

        // Assertion
        Assertions.assertEquals(accounts, result);
        Mockito.verify(accountRepository).findAllByUserId(1L);
    }
}