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
        System.out.println("-----------");
        return result;
    }

    public int mainMenu() {
        System.out.println("Please choose a number option and hit enter");
        System.out.println("1: Start a new game");
        System.out.println("2: Show the session win leader board");
        System.out.println("3: Add a new player");
        System.out.println("4: End gaming session for the day!");
        int selection = getInt("What option do you choose: ", 4);
        System.out.println("-----------");
        return selection;
    }

    public int inGameMenu() {
        System.out.println("1: Update all players scores");
        System.out.println("2: Update specific player's score");
        System.out.println("3: Check game leaderboard");
        System.out.println("4: Check session win leaderboad");
        System.out.println("5: End current round and print standings");

        int option = getInt("What option do you choose: ", 5);
        System.out.println("---------");

        return option;
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
    public void printOpeningScreen() {
        System.out.println(" .----------------.  .----------------.  .----------------.  .----------------.   .-----------------. .----------------.  .----------------.  .----------------.  .----------------. ");
        System.out.println("| .--------------. || .--------------. || .--------------. || .--------------. | | .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |");
        System.out.println("| |    ______    | || |      __      | || | ____    ____ | || |  _________   | | | | ____  _____  | || |     _____    | || |    ______    | || |  ____  ____  | || |  _________   | |");
        System.out.println("| |  .' ___  |   | || |     /  \\     | || ||_   \\  /   _|| || | |_   ___  |  | | | ||_   \\|_   _| | || |    |_   _|   | || |  .' ___  |   | || | |_   ||   _| | || | |  _   _  |  | |");
        System.out.println("| | / .'   \\_|   | || |    / /\\ \\    | || |  |   \\/   |  | || |   | |_  \\_|  | | | |  |   \\ | |   | || |      | |     | || | / .'   \\_|   | || |   | |__| |   | || | |_/ | | \\_|  | |");
        System.out.println("| | | |    ____  | || |   / ____ \\   | || |  | |\\  /| |  | || |   |  _|  _   | | | |  | |\\ \\| |   | || |      | |     | || | | |    ____  | || |   |  __  |   | || |     | |      | |");
        System.out.println("| | \\ `.___]  _| | || | _/ /    \\ \\_ | || | _| |_\\/_| |_ | || |  _| |___/ |  | | | | _| |_\\   |_  | || |     _| |_    | || | \\ `.___]  _| | || |  _| |  | |_  | || |    _| |_     | |");
        System.out.println("| |  `._____.'   | || ||____|  |____|| || ||_____||_____|| || | |_________|  | | | ||_____|\\____| | || |    |_____|   | || |  `._____.'   | || | |____||____| | || |   |_____|    | |");
        System.out.println("| |              | || |              | || |              | || |              | | | |              | || |              | || |              | || |              | || |              | |");
        System.out.println("| '--------------' || '--------------' || '--------------' || '--------------' | | '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |");
        System.out.println(" '----------------'  '----------------'  '----------------'  '----------------'   '----------------'  '----------------'  '----------------'  '----------------'  '----------------' ");
    }
}
