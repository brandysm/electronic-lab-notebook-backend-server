package com.github.brandysm.electroniclabnotebook.backendserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.brandysm.electroniclabnotebook.backendserver.dataaccessobject.OrganizationRepository;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Organization;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;
    
    public List<Organization> getOrganizations() {
        return organizationRepository.findAll();
    }
}
