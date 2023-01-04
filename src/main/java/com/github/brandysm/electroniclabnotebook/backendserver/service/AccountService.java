package com.github.brandysm.electroniclabnotebook.backendserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.brandysm.electroniclabnotebook.backendserver.dataaccessobject.AccountRepository;
import com.github.brandysm.electroniclabnotebook.backendserver.dataaccessobject.AccountRoleRepository;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Account;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.AccountRole;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found in database.");
        }
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (AccountRole accountRole : account.getAccountRoles()) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(accountRole.getName()));
        }
        return new User(account.getUsername(), account.getPassword(), simpleGrantedAuthorities);
    }

    public Account encodeAccountPassword(Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        return account;
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(String username) {
        accountRepository.deleteByUsername(username);
    }

    public Account getAccount(String username) {
        return accountRepository.findByUsername(username);
    }

    public Account getOtherAccount(Long accountId, Account account) {
        return accountRepository.findByIdAndOrganizationOrTeamsIn(accountId, account.getOrganization(),
                account.getTeams());
    }

    public List<Account> getOtherAccounts(Account account) {
        return accountRepository.findByOrganizationOrTeamsIn(account.getOrganization(), account.getTeams());
    }

    public List<AccountRole> getAccountRoles() {
        return accountRoleRepository.findAll();
    }
}
