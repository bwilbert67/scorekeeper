package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Session {
    private List<Player> players;
    private boolean isLowestWins;
    private List<Player> activePlayers;
    public Session() {

    }

    public Session(List<Player> players, boolean isLowestWins) {
        this.players = players;
        this.isLowestWins = isLowestWins;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public boolean isLowestWins() {
        return isLowestWins;
    }

    public void setLowestWins(boolean lowestWins) {
        isLowestWins = lowestWins;
    }

    public List<Player> getActivePlayers() {
        return activePlayers;
    }

    public void setActivePlayers(List<Player> activePlayers) {
        this.activePlayers = activePlayers;
    }

    public void printFinalLeaderboard() {
        players.sort(Comparator.comparingInt(Player::getWins).reversed());

        // Print medals for the top 3 players
        for (int i = 0; i < 3; i++) {
            System.out.println(players.get(i).getName() + " - " + players.get(i).getWins() + " wins");
            printMedal(i + 1);
            System.out.println();
        }

        // Rest of the players
        for (int i = 3; i < players.size(); i++) {
            System.out.println((i + 1) + ". " + players.get(i).getName() + " - " + players.get(i).getWins() + " wins");
        }
    }

    private void printMedal(int rank) {
        if (rank < 1 || rank > 3) {
            return;  // We only print medals for ranks 1, 2, and 3
        }

        System.out.println("   ____  ");
        System.out.println("  /\\  /\\  ");
        System.out.println(" /  \\/  \\ ");
        System.out.println(" \\      / ");
        System.out.println("  \\ " + rank + "  /  ");
        System.out.println("   \\/\\/  ");
    }

    public void printCurrrentSessionLeaderboard() {
        players.sort(Comparator.comparingInt(Player::getWins).reversed());

        for (Player player : players) {
            System.out.println(player.getName() + " - " + player.getWins() + " wins");
        }
        System.out.println("----------");
    }

    public List<Player> printGameLeaderborad() {
         if(isLowestWins) {
             activePlayers.sort(Comparator.comparingLong(Player::getScore));
         } else {
             activePlayers.sort(Comparator.comparingLong(Player::getScore).reversed());
         }
         for(int i = 0; i < activePlayers.size(); i++) {
             Player curPlayer = activePlayers.get(i);
             System.out.println((i + 1) + ". " + curPlayer.getName() + " " + curPlayer.getScore());
         }
        System.out.println("__________");
         //return winning player (multiple if there is a tie)
        List<Player> result = new ArrayList<>();
        //the list is sorted as of now, so first player will always get added
        result.add(activePlayers.get(0));
        for(int i = 0; i < activePlayers.size() - 1; i++) {
            //now we are checking if there is a tie. If so, that means each suceeding player that ties with
            //the first person in the list (winner) needs to be added
            if(activePlayers.get(i).getScore() != activePlayers.get(i).getScore()) {
                break;
            } else {
                result.add(activePlayers.get(i + 1));
            }
        }
         return result;
    }

    public void updateWinner(List<Player> winners) {
        for(Player cur: winners) {
            int index = activePlayers.indexOf(cur);
            activePlayers.get(index).addWin();
        }
    }
    public Player findActivePlayerByName(String name) {
        for(Player cur : activePlayers) {
            if(cur.getName().equalsIgnoreCase(name)) {
                return cur;
            }
        }
        return null;
    }
    public Player updateActivePlayersGameScore(String name, long newScore) {
        Player toChange = findActivePlayerByName(name);
        toChange.setScore(newScore);
        return toChange;
    }
}
