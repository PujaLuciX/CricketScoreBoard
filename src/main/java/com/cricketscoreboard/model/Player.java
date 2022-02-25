package com.cricketscoreboard.model;

public class Player {
    private String id, name;
    private String teamId;
    private int battingOrder;
    private boolean isOut;

    public Player(String id, String name, String teamId, int battingOrder) {
        this.id = id;
        this.name = name;
        this.battingOrder = battingOrder;
        this.teamId = teamId;
        this.isOut = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBattingOrder() {
        return battingOrder;
    }

    public void setBattingOrder(int battingOrder) {
        this.battingOrder = battingOrder;
    }

    public boolean isOut() {
        return isOut;
    }

    public void setOut() {
        this.isOut = true;
    }
}
