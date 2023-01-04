package com.github.brandysm.electroniclabnotebook.backendserver.applicationprogramminginterface;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Account;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Project;
import com.github.brandysm.electroniclabnotebook.backendserver.service.AccountService;
import com.github.brandysm.electroniclabnotebook.backendserver.service.ProjectService;

@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/project")
    public List<Project> getProjects(Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        List<Project> response = projectService.getProjects(account);
        response.forEach(t -> t.setExperiments(null));
        return response;
    }

    @PostMapping("/project")
    public ResponseEntity<Project> createProject(@RequestBody Project project, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        Project response = projectService.saveProject(project);
        if (account.getProjects() != null) {
            account.getProjects().add(response);
        } else {
            account.setProjects(Arrays.asList(response));
        }
        accountService.saveAccount(account);
        response.setAccounts(null);
        return ResponseEntity.created(null).body(response);
    }

    @PutMapping("/project/{projectId}/account/{accountId}")
    public Project addAccountToProject(@PathVariable Long projectId, @PathVariable Long accountId, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        Project response = projectService.getProject(projectId, account);
        Account otherAccount = accountService.getOtherAccount(accountId, account);
        if (otherAccount.getProjects() != null) {
            otherAccount.getProjects().add(response);
        } else {
            account.setProjects(Arrays.asList(response));
        }
        accountService.saveAccount(otherAccount);
        return response;
    }

    @DeleteMapping("/project")
    public void deleteProjects(Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        projectService.deleteProjects(account);
    }

    @GetMapping("/project/{id}")
    public Project getProject(@PathVariable Long id, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        Project response = projectService.getProject(id, account);
        response.setAccounts(null);
        return response;
    }

    @PatchMapping("/project/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project project, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        Project project2 = projectService.getProject(id, account);
        if (project2 != null) {
            if (project.getName() != null)
                project2.setName(project.getName());
            if (project.getOrganization() != null)
                project2.setOrganization(project.getOrganization());
            if (project.getTeam() != null)
                project2.setTeam(project.getTeam());
            Project response = projectService.saveProject(project2);
            response.setAccounts(null);
            return response;
        }
        return null;
    }

    @DeleteMapping("/project/{id}")
    public void deleteProject(@PathVariable Long id, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        projectService.deleteProject(id, account);
    }
}
