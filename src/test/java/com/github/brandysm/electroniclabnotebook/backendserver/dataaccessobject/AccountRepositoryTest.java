package com.github.brandysm.electroniclabnotebook.backendserver.dataaccessobject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository testedAccountRepository;

    // @Test
    // void test() {
    //     assertEquals(1, testedAccountRepository.count()); // TODO
    // }
}
