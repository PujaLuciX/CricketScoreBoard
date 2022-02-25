package com.cricketscoreboard.model;

public class Match {

    private String id, teamId1, teamId2;
    private int totalOvers;
    private String wonByTeamId;

    public Match(String id, String teamId1, String teamId2, int totalOvers) {
        this.id = id;
        this.teamId1 = teamId1;
        this.teamId2 = teamId2;
        this.totalOvers = totalOvers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamId1() {
        return teamId1;
    }

    public void setTeamId1(String teamId1) {
        this.teamId1 = teamId1;
    }

    public String getTeamId2() {
        return teamId2;
    }

    public void setTeamId2(String teamId2) {
        this.teamId2 = teamId2;
    }

    public int getTotalOvers() {
        return totalOvers;
    }

    public void setTotalOvers(int totalOvers) {
        this.totalOvers = totalOvers;
    }

    public String getWonByTeamId() {
        return wonByTeamId;
    }

    public void setWonByTeamId(String wonByTeamId) {
        this.wonByTeamId = wonByTeamId;
    }
}
