package com.github.brandysm.electroniclabnotebook.backendserver.dataaccessobject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
