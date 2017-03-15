package se.keroprog.webb.yahtzeebackend.Service;

import se.keroprog.webb.yahtzeebackend.Repository.GameLog;
import se.keroprog.webb.yahtzeebackend.Repository.Player;

import java.util.List;

/**
 *
 * Created by Kristoffer on 2017-03-07.
 */
public interface GameLogDAO {
    List<GameLog> listGameLogs(int gameIdString);

    long insertGameSession();

    void logRound(int gameID, int currentRound, int playerID, String diceString, String setString, int score);

    void updateGameLog(int gameID, List<Player> players);
}
