package org.example;

public class Player {
    private long score;
    private String name;
    private int wins;
    private boolean isPlaying;

    public Player() {

    }

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, long startingScore) {
        this.score = startingScore;
        this.name = name;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long defaultScore) {
        this.score = defaultScore;
    }

    public long addScore(long change) {
        this.score += change;
        return score;
    }

    public long subtractScore(long change) {
        this.score -= change;
        return score;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public void addWin() {
        wins++;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    @Override
    public boolean equals(Object obj) {
        // Check if the object is a reference to the current object
        if (this == obj) {
            return true;
        }

        // Check if the object is an instance of Player
        if (!(obj instanceof Player)) {
            return false;
        }

        // Cast the object to Player
        Player otherPlayer = (Player) obj;

        // Compare the name properties
        return name != null ? name.equals(otherPlayer.name) : otherPlayer.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
