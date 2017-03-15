package se.keroprog.webb.yahtzeebackend.Repository;

/**
 * Created by Kristoffer on 2017-03-07.
 */
public class PlayerLog {
    private String gameID, winner, winnerPoints, secondPlace, thirdPlace, fourthPlace, date;


    public PlayerLog() {
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getWinnerPoints() {
        return winnerPoints;
    }

    public void setWinnerPoints(String winnerPoints) {
        this.winnerPoints = winnerPoints;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }

    public String getThirdPlace() {
        return thirdPlace;
    }

    public void setThirdPlace(String thirdPlace) {
        this.thirdPlace = thirdPlace;
    }

    public String getFourthPlace() {
        return fourthPlace;
    }

    public void setFourthPlace(String fourthPlace) {
        this.fourthPlace = fourthPlace;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
