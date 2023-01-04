package com.github.brandysm.electroniclabnotebook.backendserver.service;

import com.github.brandysm.electroniclabnotebook.backendserver.dataaccessobject.AccountRepository;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    @InjectMocks
    AccountService testedAccountService;

    @Mock
    AccountRepository accountRepository; // TODO Mock autowired inside service

    // @Test
    // void addAccountTest() {
    // Account account = new Account();
    // account.setId(1L);
    // account.setName("test");
    // testedAccountService.addAccount(account); //TODO
    // }
}
