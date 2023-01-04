package com.github.brandysm.electroniclabnotebook.backendserver.applicationprogramminginterface;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.brandysm.electroniclabnotebook.backendserver.datatransferobject.Team;
import com.github.brandysm.electroniclabnotebook.backendserver.service.TeamService;

@RestController
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/team")
    public List<Team> getTeams() {
        return teamService.getTeams();
    }
}
