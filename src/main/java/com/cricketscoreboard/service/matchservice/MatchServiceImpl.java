package com.cricketscoreboard.service.matchservice;

import com.cricketscoreboard.model.*;
import com.cricketscoreboard.service.MatchService;

import java.util.*;

public class MatchServiceImpl implements MatchService {

    private Match match;
    private Team team1, team2;
    private Map<String, PlayerScore> playerOfTeam1ScoreMap;
    private Map<String, PlayerScore> playerOfTeam2ScoreMap;
    private Over over;

    public MatchServiceImpl(Match match) {
        this.match = match;
        team1 = null;
        team2 = null;
        playerOfTeam1ScoreMap = new HashMap<>();
        playerOfTeam2ScoreMap = new HashMap<>();
        over = null;
    }

    @Override
    public void addTeam1(Team team) {
        team1 = team;
        for (Player player : team.getPlayerList()) {
            playerOfTeam1ScoreMap.put(player.getId(), new PlayerScore(0, 0, 0, 0));
        }
    }

    @Override
    public void addTeam2(Team team) {
        team2 = team;
        for(Player player: team.getPlayerList()) {
            playerOfTeam2ScoreMap.put(player.getId(), new PlayerScore(0, 0, 0, 0));
        }
    }

    @Override
    public void startNewOver(String teamId, int overNumber) {
        if(over == null || !over.getPlayedByTeamId().equalsIgnoreCase(teamId)) {
            over = new Over(teamId, overNumber);
        } else {
            over.setOverNumber(overNumber);
        }
        if(teamId.equalsIgnoreCase(team1.getId())) {
            for (Player player : team1.getPlayerList()) {
                if (!player.isOut() && over.getStriker() == null) {
                    over.setStriker(player);
                } else if(!player.isOut() && over.getNonStriker() == null) {
                    over.setNonStriker(player);
                }
            }
        } else if(teamId.equalsIgnoreCase(team2.getId())) {
            for (Player player : team2.getPlayerList()) {
                if (!player.isOut() && over.getStriker() == null) {
                    over.setStriker(player);
                } else if(!player.isOut() && over.getNonStriker() == null) {
                    over.setNonStriker(player);
                }
            }
        }
    }

    private Player getNextPlayer(String teamId) {
        if(teamId.equalsIgnoreCase(team1.getId())) {
            for (Player player : team1.getPlayerList()) {
                if(!player.isOut() && !over.getNonStriker().equals(player)) {
                    return player;
                }
            }
        } else if(teamId.equalsIgnoreCase(team2.getId())) {
            for (Player player : team2.getPlayerList()) {
                if(!player.isOut() && !over.getNonStriker().equals(player)) {
                    return player;
                }
            }
        }
        return null;
    }

    @Override
    public void addRunByOver(String teamId, RunType runType) {
        Player batsman = over.getStriker();
        PlayerScore score ;
        if(teamId.equals(team1.getId())) {
            score = playerOfTeam1ScoreMap.get(batsman.getId());
        } else {
            score = playerOfTeam1ScoreMap.get(batsman.getId());
        }
        switch(runType) {
            case WIDE, NO_BALL -> {
                score.setBallsFaced(score.getBallsFaced() + 1);
                score.setScore(score.getScore() + 1);
            }
            case WICKET -> {
                score.setBallsFaced(score.getBallsFaced() + 1);
                batsman.setOut();
                if(teamId.equals(team1.getId())) {
                    team1.addWickets();
                } else {
                    team2.addWickets();
                }
                over.setStriker(getNextPlayer(teamId));
            }
        }
        if (teamId.equals(team1.getId())) {
            playerOfTeam1ScoreMap.put(batsman.getId(), score);
        } else {
            playerOfTeam2ScoreMap.put(batsman.getId(), score);
        }
    }

    @Override
    public void addRunByOver(String teamId, int run) {
        Player batsman = over.getStriker();
        PlayerScore score = playerOfTeam1ScoreMap.get(batsman.getId());
        if(team2 != null && teamId.equals(team2.getId())) {
            score = playerOfTeam2ScoreMap.get(batsman.getId());
        }

        if(run > 0 && run <= 6) {
            score.setBallsFaced(score.getBallsFaced() + 1);
            score.setScore(score.getScore() + run);
        }
        switch(run) {
            case 1,3,5 -> {
                Player temp = over.getStriker();
                over.setStriker(over.getNonStriker());
                over.setNonStriker(temp);
            }
            case 4 -> score.setFours(score.getFours() + 1);
            case 6 -> score.setSixes(score.getSixes() + 1);
        }
        if (teamId.equals(team1.getId())) {
            playerOfTeam1ScoreMap.put(batsman.getId(), score);
        } else {
            playerOfTeam2ScoreMap.put(batsman.getId(), score);
        }
    }

    @Override
    public void fetchScoreByOver(String teamId, int overNumber) {
        if(teamId.equals(team1.getId())) {
            System.out.println("Scorecard for Team 1:");
            System.out.println("Player Name \t Score \t 4s \t 6s");
            int totalScores = 0;
            for(Map.Entry mapElement: playerOfTeam1ScoreMap.entrySet()) {
                PlayerScore playerScore = (PlayerScore) mapElement.getValue();
                totalScores += playerScore.getScore();
                System.out.println(mapElement.getKey() + "\t \t \t" + playerScore.getScore() + "\t"
                    + playerScore.getFours() + "\t" + playerScore.getSixes() + "\t" + playerScore.getBallsFaced());
            }
            team1.addToTotalScores(totalScores);
            System.out.println("Total: " + team1.getTotalScores() + "/" + team1.getNumberOfWickets());
            System.out.println("Overs: " + overNumber);
        } else if(teamId.equals(team2.getId())) {
            System.out.println("Scorecard for Team 2:");
            System.out.println("Player Name \t Score \t 4s \t 6s");
            int totalScores = 0;
            for(Map.Entry mapElement: playerOfTeam2ScoreMap.entrySet()) {
                PlayerScore playerScore = (PlayerScore) mapElement.getValue();
                totalScores += playerScore.getScore();
                System.out.println(mapElement.getKey() + "\t \t \t" + playerScore.getScore() + "\t"
                        + playerScore.getFours() + "\t" + playerScore.getSixes() + "\t" + playerScore.getBallsFaced());
            }
            team2.addToTotalScores(totalScores);
            System.out.println("Total: " + team2.getTotalScores() + "/" + team2.getNumberOfWickets());
            System.out.println("Overs: " + overNumber);
        }
    }

    @Override
    public List<String> getWinner() {
        if(team1.getTotalScores() == team2.getTotalScores()) {
            return new ArrayList<>(Arrays.asList("DRAW"));
        } else if(team1.getTotalScores() > team2.getTotalScores()) {
            match.setWonByTeamId(team1.getId());
            return new ArrayList<>(Arrays.asList("Team 1", "" + (team1.getTotalScores() - team2.getTotalScores())));
        } else {
            match.setWonByTeamId(team2.getId());
            return new ArrayList<>(Arrays.asList("Team 2", "" + (team2.getTotalPlayers() - team2.getNumberOfWickets())));
        }
    }

    /*
    If scores equals - match is draw
    if team1 is winning by runs
    if team2 is winning by wicket
     */

}
