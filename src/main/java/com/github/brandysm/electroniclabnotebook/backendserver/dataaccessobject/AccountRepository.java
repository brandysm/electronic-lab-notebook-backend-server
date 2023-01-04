package com.github.brandysm.electroniclabnotebook.backendserver.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Account;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Organization;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Team;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
    void deleteByUsername(String username);
    List<Account> findByOrganizationOrTeamsIn(Organization organization, List<Team> teams);
    @Query("SELECT a FROM Account a JOIN a.teams t WHERE a.id = :id AND (a.organization = :organization OR t IN (:teams))")
    Account findByIdAndOrganizationOrTeamsIn(Long id, Organization organization, List<Team> teams);
}
