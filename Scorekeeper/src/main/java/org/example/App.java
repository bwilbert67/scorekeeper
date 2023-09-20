package org.example;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        CLIUtil scanner = new CLIUtil();
        System.out.println("~Shall we play a game?~");

        //Creates initial player list; Mass adding many players at once.
        Game thisGame = new Game();
        List<Player> players = scanner.getPlayerNames();
        thisGame.setPlayers(players);

        boolean isSessionActive = true;
        while(isSessionActive) {
           int selection = scanner.mainMenu();

           if(selection == 1) {
               //start a new round
               boolean isGameActive = true;
               while(isGameActive) {
                   scanner.inGameMenu();
               }
           } else if (selection == 2) {
               // show the current leaderboard
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
        thisGame.printLeaderboard();
    }
}
