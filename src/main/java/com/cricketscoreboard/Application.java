package com.cricketscoreboard;

import com.cricketscoreboard.model.*;
import com.cricketscoreboard.service.MatchService;
import com.cricketscoreboard.service.matchservice.MatchServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Number of players for team: ");
        int numOfPlayersPerTeam = sc.nextInt();

        System.out.println("Number of overs: ");
        int numOfOvers = sc.nextInt();
        String teamId1 = "Team1", teamId2 = "Team2";

        MatchService matchService = new MatchServiceImpl(
                new Match("1", teamId1, teamId2, numOfOvers)
        );

        ArrayList<Player> playersOfTeam1 = new ArrayList<>();
        System.out.println("Batting Order for team 1: ");
        for(int i = 1; i <= numOfPlayersPerTeam; i++) {
            String id = sc.next();
            playersOfTeam1.add(new Player(id, id, teamId1, i));
        }

        matchService.addTeam1(
                new Team(teamId1, teamId1, numOfPlayersPerTeam, playersOfTeam1)
        );

        for(int over = 1; over <= numOfOvers; over++) {
            System.out.println("Over " + over + ":");
            matchService.startNewOver(teamId1, over);
            int balls = 6;
            for(int i = 0; i < balls; i++) {
                String run = sc.next();
                if(run.equalsIgnoreCase("W")) {
                    matchService.addRunByOver(teamId1, RunType.WICKET);
                } else if(run.equalsIgnoreCase("Wd")) {
                    matchService.addRunByOver(teamId1, RunType.WIDE);
                    balls++;
                } else if(run.equalsIgnoreCase("Nb")) {
                    matchService.addRunByOver(teamId1, RunType.NO_BALL);
                    balls++;
                } else {
                    matchService.addRunByOver(teamId1, Integer.parseInt(run));
                }
                checkWinner(matchService);
            }
            matchService.fetchScoreByOver(teamId1, over);
        }

        ArrayList<Player> playersOfTeam2 = new ArrayList<>();
        System.out.println("Batting Order for team 2: ");
        for(int i = numOfPlayersPerTeam+1; i <= 2*numOfPlayersPerTeam; i++) {
            String id = sc.next();
            playersOfTeam2.add(new Player(id, id, teamId2, i));
        }

        matchService.addTeam2(
                new Team(teamId2, teamId2, numOfPlayersPerTeam, playersOfTeam2)
        );

        for(int over = 1; over <= numOfOvers; over++) {
            System.out.println("Over " + over + ":");
            matchService.startNewOver(teamId2, over);
            for(int i = 0; i < 6; i++) {
                String run = sc.next();
                if(run.equalsIgnoreCase("W")) {
                    matchService.addRunByOver(teamId2, RunType.WICKET);
                } else if(run.equalsIgnoreCase("Wd")) {
                    matchService.addRunByOver(teamId2, RunType.WIDE);
                } else if(run.equalsIgnoreCase("No_Ball")) {
                    matchService.addRunByOver(teamId2, RunType.NO_BALL);
                } else {
                    matchService.addRunByOver(teamId2, Integer.parseInt(run));
                }
                checkWinner(matchService);
            }
            matchService.fetchScoreByOver(teamId2, over);
        }
    }

    public static void checkWinner(MatchService matchService) {
        List<String> result = matchService.getWinner();
        if(result.size() == 1) {
            System.out.println("Draw");
        } else {
            if(result.get(0).equalsIgnoreCase("Team 1")) {
                System.out.println("Result: " + result.get(0) + " won the match by " + result.get(1) + "runs");
            } else {
                System.out.println("Result: " + result.get(0) + " won the match by " + result.get(1) + "wickets");
            }
        }
    }

}
