package com.github.brandysm.electroniclabnotebook.backendserver.applicationprogramminginterface;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Account;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Experiment;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Project;
import com.github.brandysm.electroniclabnotebook.backendserver.service.AccountService;
import com.github.brandysm.electroniclabnotebook.backendserver.service.ExperimentService;
import com.github.brandysm.electroniclabnotebook.backendserver.service.ProjectService;

@RestController
public class ExperimentController {
    @Autowired
    private ExperimentService experimentService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/project/{projectId}/experiment")
    public List<Experiment> getExperiments(@PathVariable Long projectId, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        Project project = projectService.getProject(projectId, account);
        if (project == null) return null;
        List<Experiment> response = experimentService.getExperiments(project);
        response.forEach(t -> t.setDocuments(null));
        return response;
    }

    @PostMapping("/project/{projectId}/experiment")
    public ResponseEntity<Experiment> createExperiment(@PathVariable Long projectId, @RequestBody Experiment experiment, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        Project project = projectService.getProject(projectId, account);
        if (project == null) return null;
        experiment.setCreationDate(new Date());
        Experiment response = experimentService.saveExperiment(experiment);
        if (project.getExperiments() != null) {
            project.getExperiments().add(response);
        } else {
            project.setExperiments(Arrays.asList(response));
        }
        projectService.saveProject(project);
        response.setProjects(null);
        return ResponseEntity.created(null).body(response);
    }

    @DeleteMapping("/project/{projectId}/experiment")
    public void deleteExperiments(@PathVariable Long projectId, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        Project project = projectService.getProject(projectId, account);
        experimentService.deleteExperiments(project);
    }

    @GetMapping("/project/{projectId}/experiment/{experimentId}")
    public Experiment getExperiment(@PathVariable Long projectId, @PathVariable Long experimentId, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        Project project = projectService.getProject(projectId, account);
        Experiment response = experimentService.getExperiment(experimentId, project);
        response.setProjects(null);
        return response;
    }

    @PatchMapping("/project/{projectId}/experiment/{experimentId}")
    public Experiment updateExperiment(@PathVariable Long projectId, @PathVariable Long experimentId, @RequestBody Experiment experiment, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        Project project = projectService.getProject(projectId, account);
        Experiment experiment2 = experimentService.getExperiment(experimentId, project);
        if (experiment2 != null) {
            if (experiment.getName() != null)
                experiment2.setName(experiment.getName());
            Experiment response = experimentService.saveExperiment(experiment2);
            response.setProjects(null);
            return response;
        }
        return null;
    }

    @DeleteMapping("/project/{projectId}/experiment/{experimentId}")
    public void deleteExperiment(@PathVariable Long projectId, @PathVariable Long experimentId, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        Project project = projectService.getProject(projectId, account);
        experimentService.deleteExperiment(experimentId, project);
    }
}
