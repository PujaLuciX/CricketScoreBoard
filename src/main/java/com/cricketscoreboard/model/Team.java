package com.cricketscoreboard.model;

import java.util.List;

public class Team {

    private String id, name;
    private int totalPlayers;
    private List<Player> playerList;
    private int numberOfWickets;
    private int totalScores;

    public Team(String id, String name, int totalPlayers, List<Player> playerList) {
        this.id = id;
        this.name = name;
        this.totalPlayers = totalPlayers;
        this.playerList = playerList;
        this.numberOfWickets = 0;
        this.totalScores = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalPlayers() {
        return totalPlayers;
    }

    public void setTotalPlayers(int totalPlayers) {
        this.totalPlayers = totalPlayers;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public void addPlayer(Player player) {
        this.playerList.add(player);
    }

    public int getNumberOfWickets() {
        return numberOfWickets;
    }

    public void addWickets() {
        this.numberOfWickets += 1;
    }

    public int getTotalScores() {
        return totalScores;
    }

    public void addToTotalScores(int score) {
        this.totalScores += score;
    }
}
