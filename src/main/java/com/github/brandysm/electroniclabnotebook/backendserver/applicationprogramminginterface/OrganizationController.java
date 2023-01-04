package com.github.brandysm.electroniclabnotebook.backendserver.applicationprogramminginterface;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Organization;
import com.github.brandysm.electroniclabnotebook.backendserver.service.OrganizationService;

@RestController
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;
    
    @GetMapping("/organization")
    public List<Organization> getOrganizations() {
        return organizationService.getOrganizations();
    }
}
