package com.github.brandysm.electroniclabnotebook.backendserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.brandysm.electroniclabnotebook.backendserver.dataaccessobject.ProjectRepository;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Account;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Project;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public Project getProject(Long id, Account account) {
        Project project = projectRepository.findById(id).get();
        if (project.getAccounts().contains(account)) {
            return project;
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteProject(Long id, Account account) {
        Project project = projectRepository.findById(id).get();
        if (project.getAccounts().contains(account) && project.getAccounts().size() == 1) {
            projectRepository.deleteById(id);
            account.getProjects().remove(project);
        } else {
            account.getProjects().remove(project);
        }
    }

    public List<Project> getProjects(Account account) {
        return projectRepository.findByAccounts(account);
    }

    @Transactional
    public void deleteProjects(Account account) {
        List<Project> projectListToDelete = projectRepository.findByAccounts(account);
        for (Project project : projectListToDelete) {
            if(project.getAccounts().size() == 1) {
                projectRepository.deleteById(project.getId());
            }
        }
        account.setProjects(null);
    }
}
