package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        Session thisSession = new Session();
        CLIUtil scanner = new CLIUtil();
        scanner.printOpeningScreen();
        System.out.println("~Shall we play a game?~");

        // TODO Add in ASCII GAME NIGHT

        //Creates initial player list; Mass adding many players at once.
        List<Player> players = scanner.getPlayerNames();
        thisSession.setPlayers(players);

        boolean isSessionActive = true;
        while(isSessionActive) {
           int selection = scanner.mainMenu();

           if(selection == 1) {
               //start a new round
               boolean isGameActive = true;
               boolean  isLowestWins = scanner.getString("Does lowest score win? y or n: ").equalsIgnoreCase("y");
               thisSession.setLowestWins(isLowestWins);

               //checking if anyone is sitting out. Then making a list of active players
               boolean isSittingOut = scanner.getString("Is anyone sitting out? y or n: ").equalsIgnoreCase("y");
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
               System.out.println("  ");
               while(isGameActive) {
                   int gameMenuSelection = scanner.inGameMenu();
                   if(gameMenuSelection == 1) {
                       System.out.println("Please enter the amount each score should change by.");
                       System.out.println("EX: -15 will subtract 15, but 15 will add 15");
                       List<Long> scoreChanges = new ArrayList<>();
                       List<Player> temp = thisSession.getActivePlayers();
                       //gathering new scores so I can tell user and they can double check
                       for(Player cur : temp) {
                           long scoreChange = scanner.getLong(cur.getName() + ": ");
                           scoreChanges.add(scoreChange);
                       }
                       for(int i = 0; i < scoreChanges.size(); i++) {
                           long change = scoreChanges.get(i);
                           long current = temp.get(i).getScore();
                           System.out.println(temp.get(i).getName() + ": " + (scoreChanges.get(i) + temp.get(i).getScore()));
                       }
                       String scoresRight = scanner.getString("Do these ^^ new scores looks right? Y or n?: ");
                       if(scoresRight.equalsIgnoreCase("y")) {
                           for(int i = 0; i < scoreChanges.size(); i++) {
                               Player cur = temp.get(i);
                               long scoreChange = scoreChanges.get(i);
                               cur.changeScore(scoreChange);
                           }
                       } else {
                           System.out.println("Oh no, try again!");
                       }
                       thisSession.printGameLeaderborad();
                   } else if(gameMenuSelection == 2) {
                       thisSession.printGameLeaderborad();
                       //TODO Fix problem where a player is entered that does no exist
                       String whoToChange = scanner.getString("Type the name of the player who's " +
                               "score you wish to change: ");
                       long newScore = scanner.getLong("What should their score be?: ");
                       Player withNewScore = thisSession.updateActivePlayersGameScore(whoToChange, newScore);
                       System.out.println("Players new score was set to " + withNewScore.getScore());
                   } else if(gameMenuSelection == 3) {
                       thisSession.printGameLeaderborad();
                   } else if(gameMenuSelection == 4) {
                       thisSession.printCurrrentSessionLeaderboard();
                   } else if(gameMenuSelection == 5) {
                       String doubleCheck = scanner.getString("Are you sure you want to end this game? All data " +
                               "will be lost! Enter y or n: ");
                       if(doubleCheck.equalsIgnoreCase("y")) {
                           isGameActive = false;
                       } else {
                           System.out.println("Phew, let's keep going!");
                       }
                   }
               }
               //check who won and update their win total in the players list
               //TODO is giving eveyrone a win 
                List<Player> winners = thisSession.printGameLeaderborad();
                thisSession.updateWinner(winners);

                //current game is over
               System.out.println("Good game!");
           } else if (selection == 2) {
               // show the current leaderboard
               thisSession.printCurrrentSessionLeaderboard();
           } else if (selection == 3) {
               //TODO add a new player
           } else if (selection == 4) {
               //end game
               String response = scanner.getString("Are you sure you are done for the day? Game data will " +
                       "be lost! Enter Y if yes or N if no: ");
               if(response.equalsIgnoreCase("y")) {
                   isSessionActive = false;
               } else {
                   System.out.println("Phew, lets keep playing then");
               }
           }
        }

        //print final win standings
        thisSession.printFinalLeaderboard();
    }
}
