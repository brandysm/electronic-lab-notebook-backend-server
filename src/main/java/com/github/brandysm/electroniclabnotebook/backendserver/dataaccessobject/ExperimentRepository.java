package com.github.brandysm.electroniclabnotebook.backendserver.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Experiment;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Project;

@Repository
public interface ExperimentRepository extends JpaRepository<Experiment, Long> {
    List<Experiment> findByProjects(Project project);
    @Transactional
    void deleteByProjects(Project project);
}
