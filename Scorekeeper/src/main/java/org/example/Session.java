package org.example;

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

    public void printOverallLeaderboard() {
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
    }

    public Player printGameLeaderborad() {
         if(isLowestWins) {
             activePlayers.sort(Comparator.comparingLong(Player::getScore));
         } else {
             activePlayers.sort(Comparator.comparingLong(Player::getScore).reversed());
         }
         for(int i = 0; i < activePlayers.size(); i++) {
             Player curPlayer = activePlayers.get(i);
             System.out.println((i + 1) + ". " + curPlayer + " " + curPlayer.getScore());
         }
         //return winning player
         return activePlayers.get(0);
    }

    public void updateWinner(Player winner) {
        int index = activePlayers.indexOf(winner);
        activePlayers.get(index).addWin();
    }
}
