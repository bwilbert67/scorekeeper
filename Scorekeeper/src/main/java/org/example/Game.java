package org.example;

import java.util.Comparator;
import java.util.List;

public class Game {
    private List<Player> players;
    private boolean isLowestWins;

    public Game() {

    }

    public Game(List<Player> players, boolean isLowestWins) {
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

    public void printLeaderboard() {
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

}
