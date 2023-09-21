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
    public int getInt(String message, int maxMenuOption) {
        while (true) {
            try {
                System.out.print(message);
                int result = userInput.nextInt();
                userInput.nextLine();

                if (result <= maxMenuOption) {
                    return result;
                } else {
                    System.out.println("Please enter a valid menu option");
                }

            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid number.");
                userInput.nextLine();
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

    public List<Player> getPlayerNames() {
        long playerCount = getLong("How many players?: ");
        List<Player> result = new ArrayList<>();
        for(long i = 1; i <= playerCount; i++) {
            String name = getString("Player " + i + ": ");
            Player curPlayer = new Player(name);
            result.add(curPlayer);
        }
        return result;
    }

    public int mainMenu() {
        System.out.println("Please choose a number option and hit enter");
        System.out.println("1: Start a new game");
        System.out.println("2: Show the session win leader board");
        System.out.println("3: Add a new player");
        System.out.println("4: End gaming session");
        int selection = getInt("What option do you choose: ", 4);
        return selection;
    }

    public int inGameMenu() {
        System.out.println("1: Update all players scores");
        System.out.println("2: Update specific player's score");
        System.out.println("3: Check game leaderboard");
        System.out.println("4: Check session win leaderboad");
        System.out.println("5: End game and print winners");

        return getInt("What option do you choose", 5);
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
