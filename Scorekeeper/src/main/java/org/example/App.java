package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        CLIUtil scanner = new CLIUtil();
        System.out.println("~Shall we play a game?~");

        //Creates initial player list; Mass adding many players at once.
        Session thisSession = new Session();
        List<Player> players = scanner.getPlayerNames();
        thisSession.setPlayers(players);

        boolean isSessionActive = true;
        while(isSessionActive) {
           int selection = scanner.mainMenu();

           if(selection == 1) {
               //start a new round
               boolean isGameActive = true;
               boolean  isLowestWins = scanner.getString("Does lowest score win? Y or n: ").equalsIgnoreCase("y");
               thisSession.setLowestWins(isLowestWins);

               //checking if anyone is sitting out. Then making a list of active players
               boolean isSittingOut = scanner.getString("Is anyone sitting out? Y or n: ").equalsIgnoreCase("y");
               List<Player> playing = new ArrayList<>(thisSession.getPlayers());

               if(isSittingOut) {
                   String stringSittingOut = scanner.getString("List who is sitting out in a comma separated list (Ex. Ben,Sam,Alex): ");
                   List<String> sittingOut = Arrays.asList(stringSittingOut.split(","));
                   for(int i = 0; i < playing.size(); i++) {
                       String curName = playing.get(i).getName();
                       if(sittingOut.contains(curName)) {
                           playing.remove(i);
                       }
                   }
               }
               //playing List now has all active players. Now we just need to set their default scores
               long defaultScore = scanner.getLong("What score should everyone start at: ");
               for(Player cur : playing) {
                   cur.setScore(defaultScore);
               }
               thisSession.setActivePlayers(playing);
               while(isGameActive) {
                   scanner.inGameMenu();
                   // TODO update score of each player each round
               }
               //check who won and update their win total in the players list
                Player winner = thisSession.printGameLeaderborad();
                thisSession.updateWinner(winner);

                //current game is over
               System.out.println("Good game!");
           } else if (selection == 2) {
               // show the current leaderboard
               thisSession.printCurrrentSessionLeaderboard();
           } else if (selection == 3) {
               //add a new player
           } else if (selection == 4) {
               //end game
               String response = scanner.getString("Are you sure? Game data will " +
                       "be lost. Enter Y if yes or N if no");
               if(response.equalsIgnoreCase("y")) {
                   isSessionActive = false;
               } else {
                   System.out.println("Phew, lets keep playing then");
               }
           }
        }

        //print final win standings
        thisSession.printOverallLeaderboard();
    }
}
