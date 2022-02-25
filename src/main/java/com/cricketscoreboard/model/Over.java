package com.cricketscoreboard.model;

import java.util.UUID;

public class Over {
    private String id;
    private String playedByTeamId;
    private int overNumber;
    private Player striker;
    private Player nonStriker;

    public Over(String playedByTeamId, int overNumber) {
        this.id = UUID.randomUUID().toString();
        striker = null;
        nonStriker = null;
        this.playedByTeamId = playedByTeamId;
        this.overNumber = overNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayedByTeamId() {
        return playedByTeamId;
    }

    public void setPlayedByTeamId(String playedByTeamId) {
        this.playedByTeamId = playedByTeamId;
    }

    public int getOverNumber() {
        return overNumber;
    }

    public void setOverNumber(int overNumber) {
        this.overNumber = overNumber;
    }

    public Player getStriker() {
        return striker;
    }

    public void setStriker(Player striker) {
        this.striker = striker;
    }

    public Player getNonStriker() {
        return nonStriker;
    }

    public void setNonStriker(Player nonStriker) {
        this.nonStriker = nonStriker;
    }
}
