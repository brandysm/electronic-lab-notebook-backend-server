package com.github.brandysm.electroniclabnotebook.backendserver.dataaccessobject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
