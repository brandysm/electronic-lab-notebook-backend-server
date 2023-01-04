package com.github.brandysm.electroniclabnotebook.backendserver.applicationprogramminginterface;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Account;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.AccountRole;
import com.github.brandysm.electroniclabnotebook.backendserver.service.AccountService;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/account")
    public List<Account> getCurrentAccounts(Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        account.setPassword(null);
        account.setProjects(null);
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        return accounts;
    }

    @GetMapping("/account/other")
    public List<Account> getOtherAccounts(Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        List<Account> response = accountService.getOtherAccounts(account);
        response.remove(account);
        response.forEach(t -> {
            t.setPassword(null);
            t.setProjects(null);
            t.setOrganization(null);
            t.setTeams(null);
        });
        return response;
    }

    @GetMapping("/accountrole")
    public List<AccountRole> getAccountRoles() {
        return accountService.getAccountRoles();
    }

    @PostMapping("/account")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        account = accountService.encodeAccountPassword(account);
        Account response = accountService.saveAccount(account);
        account.setPassword(null);
        return ResponseEntity.created(null).body(response);
    }
}
