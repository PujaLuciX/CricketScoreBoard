package com.cricketscoreboard.model;

import java.util.UUID;

public class Run {
    private String id;
    private String runTakenByPlayerId;
    private RunType runType;
    private int runTaken;

    public Run(String runTakenByPlayerId, int runTaken) {
        this.id = UUID.randomUUID().toString();
        this.runTakenByPlayerId = runTakenByPlayerId;
        this.runTaken = runTaken;
    }

    public Run(String runTakenByPlayerId, RunType runType) {
        this.id = UUID.randomUUID().toString();
        this.runTakenByPlayerId = runTakenByPlayerId;
        this.runType = runType;
    }

    public int getRunTaken() {
        return runTaken;
    }

    public String getRunTakenByPlayerId() {
        return runTakenByPlayerId;
    }

    public RunType getRunType() {
        return runType;
    }
}
