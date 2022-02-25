package com.cricketscoreboard.service;

import com.cricketscoreboard.model.Over;
import com.cricketscoreboard.model.Run;
import com.cricketscoreboard.model.RunType;
import com.cricketscoreboard.model.Team;

import java.util.List;

public interface MatchService {

    public void addTeam1(Team team);

    public void addTeam2(Team team);

    public void startNewOver(String teamId, int overNumber);

    public void addRunByOver(String teamId, RunType runType);

    public void addRunByOver(String teamId, int run);

    public void fetchScoreByOver(String teamId, int overNumber);

    public List<String> getWinner();
}
