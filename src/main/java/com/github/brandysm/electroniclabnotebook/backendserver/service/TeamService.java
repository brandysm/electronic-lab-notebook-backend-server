package com.github.brandysm.electroniclabnotebook.backendserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.brandysm.electroniclabnotebook.backendserver.dataaccessobject.TeamRepository;
import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Team;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getTeams() {
        return teamRepository.findAll();
    }
}
