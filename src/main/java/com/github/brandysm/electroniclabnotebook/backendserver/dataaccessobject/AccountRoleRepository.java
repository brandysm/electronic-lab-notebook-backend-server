package com.github.brandysm.electroniclabnotebook.backendserver.dataaccessobject;

import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.AccountRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole, Long> {
    AccountRole findByName(String name);
}
