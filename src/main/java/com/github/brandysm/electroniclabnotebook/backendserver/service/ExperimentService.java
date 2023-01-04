package com.github.brandysm.electroniclabnotebook.backendserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.brandysm.electroniclabnotebook.backendserver.dataaccessobject.ExperimentRepository;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Experiment;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Project;

@Service
public class ExperimentService {
    @Autowired
    private ExperimentRepository experimentRepository;

    public Experiment saveExperiment(Experiment experiment) {
        return experimentRepository.save(experiment);
    }

    public Experiment getExperiment(Long id, Project project) {
        Experiment experiment = experimentRepository.findById(id).get();
        if (experiment.getProjects().contains(project)) {
            return experiment;
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteExperiment(Long id, Project project) {
        Experiment experiment = experimentRepository.findById(id).get();
        if (experiment.getProjects().contains(project)) {
            experimentRepository.deleteById(id);
            project.getExperiments().remove(experiment);
        }
    }

    public List<Experiment> getExperiments(Project project) {
        return experimentRepository.findByProjects(project);
    }

    @Transactional
    public void deleteExperiments(Project project) {
        experimentRepository.deleteByProjects(project);
        project.setExperiments(null);
    }
}
