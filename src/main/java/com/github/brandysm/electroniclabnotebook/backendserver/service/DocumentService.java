package com.github.brandysm.electroniclabnotebook.backendserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.brandysm.electroniclabnotebook.backendserver.dataaccessobject.DocumentRepository;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Document;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Experiment;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public Document saveDocument(Document document) {
        return documentRepository.save(document);
    }

    public Document getDocument(Long id, Experiment experiment) {
        Document document = documentRepository.findById(id).get();
        if (document.getExperiments().contains(experiment)) {
            return document;
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteDocument(Long id, Experiment experiment) {
        Document document = documentRepository.findById(id).get();
        if (document.getExperiments().contains(experiment)) {
            documentRepository.deleteById(id);
            experiment.getDocuments().remove(document);
        }
    }

    public List<Document> getDocuments(Experiment experiment) {
        return documentRepository.findByExperiments(experiment);
    }

    @Transactional
    public void deleteDocuments(Experiment experiment) {
        documentRepository.deleteByExperiments(experiment);
        experiment.setDocuments(null);
    }
}
