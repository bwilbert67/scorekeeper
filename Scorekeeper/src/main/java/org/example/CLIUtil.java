package org.example;

import java.util.*;

public class CLIUtil {
    private Scanner userInput;

    public CLIUtil() {
        this.userInput = new Scanner(System.in);
    }

    public long getLong(String message) {
        while (true) {
            try {
                System.out.print(message);
                long result = userInput.nextLong();
                userInput.nextLine();
                return result;
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid number.");
                userInput.next();
            }
        }
    }
    public String getString(String message) {
        String result;
        while (true) {
            System.out.print(message);
            result = userInput.nextLine();
            if (!result.equals("")) {
                break;
            }
            System.out.println("Please enter a valid input");
        }
        return result;
    }

    public List<String> getPlayerNames() {
        long playerCount = getLong("How many players?: ");
        List<String> result = new ArrayList<>();
        for(long i = 1; i <= playerCount; i++) {
            String curPlayer = getString("Player " + i + ": ");
            result.add(curPlayer);
        }
        return result;
    }

    public void printPlayersAndScore(List<Player> players, boolean isLowestWins) {

        if(isLowestWins) {
            players.sort(Comparator.comparingLong(Player:: getScore));
        } else {
            players.sort(Comparator.comparingLong(Player:: getScore).reversed());
        }
        for(Player cur : players) {
            System.out.println(cur.getName() + " " + cur.getScore());
        }
    }
}
