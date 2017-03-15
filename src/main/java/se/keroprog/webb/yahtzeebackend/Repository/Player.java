package se.keroprog.webb.yahtzeebackend.Repository;

/**
 * Created by Kristoffer on 2017-03-06.
 */
public class Player {

    private int id;
    private int score;
    private int currentRound;

    public int getGameID() {
        return gameID;
    }

    private int gameID;
    private String name, password, diceString;

    public int getCurrentRound() {
        return currentRound;
    }

    public Player() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDiceString() {
        return diceString;
    }

    public void setDiceString(String diceString) {
        this.diceString = diceString;
    }
}

