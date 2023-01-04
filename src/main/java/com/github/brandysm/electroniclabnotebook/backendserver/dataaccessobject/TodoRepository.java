package com.github.brandysm.electroniclabnotebook.backendserver.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Account;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByAccount(Account account);
    @Transactional
    void deleteByAccount(Account account);
}
