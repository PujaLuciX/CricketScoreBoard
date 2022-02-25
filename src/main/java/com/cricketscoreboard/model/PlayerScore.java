package com.cricketscoreboard.model;

import java.util.UUID;

public class PlayerScore {
    private String id;
    private int score, ballsFaced, fours, sixes;

    public PlayerScore(int score, int ballsFaced, int fours, int sixes) {
        this.id = UUID.randomUUID().toString();
        this.score = score;
        this.ballsFaced = ballsFaced;
        this.fours = fours;
        this.sixes = sixes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }
}
