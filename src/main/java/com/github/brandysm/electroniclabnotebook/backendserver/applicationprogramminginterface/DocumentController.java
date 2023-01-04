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
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Document;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Experiment;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Project;
import com.github.brandysm.electroniclabnotebook.backendserver.service.AccountService;
import com.github.brandysm.electroniclabnotebook.backendserver.service.DocumentService;
import com.github.brandysm.electroniclabnotebook.backendserver.service.ExperimentService;
import com.github.brandysm.electroniclabnotebook.backendserver.service.ProjectService;

@RestController
public class DocumentController {
    @Autowired
    private DocumentService documentService;
    
    @Autowired
    private ExperimentService experimentService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/project/{projectId}/experiment/{experimentId}/document")
    public List<Document> getDocuments(@PathVariable Long projectId, @PathVariable Long experimentId, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        Project project = projectService.getProject(projectId, account);
        if (project == null) return null;
        Experiment experiment = experimentService.getExperiment(experimentId, project);
        if (experiment == null) return null;
        List<Document> response = documentService.getDocuments(experiment);
        response.forEach(t -> t.setData(null));
        return response;
    }

    @PostMapping("/project/{projectId}/experiment/{experimentId}/document")
    public ResponseEntity<Document> createDocument(@PathVariable Long projectId, @PathVariable Long experimentId, @RequestBody Document document, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        Project project = projectService.getProject(projectId, account);
        if (project == null) return null;
        Experiment experiment = experimentService.getExperiment(experimentId, project);
        if (experiment == null) return null;
        document.setCreationDate(new Date());
        Document response = documentService.saveDocument(document);
        if (experiment.getDocuments() != null) {
            experiment.getDocuments().add(response);
        } else {
            experiment.setDocuments(Arrays.asList(response));
        }
        experimentService.saveExperiment(experiment);
        response.setExperiments(null);
        return ResponseEntity.created(null).body(response);
    }

    @DeleteMapping("/project/{projectId}/experiment/{experimentId}/document")
    public void deleteDocuments(@PathVariable Long projectId, @PathVariable Long experimentId, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        Project project = projectService.getProject(projectId, account);
        Experiment experiment = experimentService.getExperiment(experimentId, project);
        documentService.deleteDocuments(experiment);
    }

    @GetMapping("/project/{projectId}/experiment/{experimentId}/document/{documentId}")
    public Document getDocument(@PathVariable Long projectId, @PathVariable Long experimentId, @PathVariable Long documentId, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        Project project = projectService.getProject(projectId, account);
        Experiment experiment = experimentService.getExperiment(experimentId, project);
        Document response = documentService.getDocument(documentId, experiment);
        response.setExperiments(null);
        return response;
    }

    @PatchMapping("/project/{projectId}/experiment/{experimentId}/document/{documentId}")
    public Document updateDocument(@PathVariable Long projectId, @PathVariable Long experimentId, @PathVariable Long documentId, @RequestBody Document document, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        Project project = projectService.getProject(projectId, account);
        Experiment experiment = experimentService.getExperiment(experimentId, project);
        Document document2 = documentService.getDocument(documentId, experiment);
        if (document2 != null) {
            if (document.getName() != null)
                document2.setName(document.getName());
            if (document.getData() != null)
                document2.setData(document.getData());
            Document response = documentService.saveDocument(document2);
            response.setExperiments(null);
            return response;
        }
        return null;
    }

    @DeleteMapping("/project/{projectId}/experiment/{experimentId}/document/{documentId}")
    public void deleteDocument(@PathVariable Long projectId, @PathVariable Long experimentId, @PathVariable Long documentId, Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        Project project = projectService.getProject(projectId, account);
        Experiment experiment = experimentService.getExperiment(experimentId, project);
        documentService.deleteDocument(documentId, experiment);
    }
}
