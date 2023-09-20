package org.example;

public class Player {
    private long score;
    private String name;

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
}
