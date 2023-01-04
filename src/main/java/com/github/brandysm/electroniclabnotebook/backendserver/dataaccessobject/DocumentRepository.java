package com.github.brandysm.electroniclabnotebook.backendserver.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Document;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Experiment;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByExperiments(Experiment experiment);
    @Transactional
    void deleteByExperiments(Experiment experiment);
}
