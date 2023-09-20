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
}
