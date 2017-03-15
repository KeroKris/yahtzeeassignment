package se.keroprog.webb.yahtzeebackend.Service;

import se.keroprog.webb.yahtzeebackend.Repository.PlayerLog;

import java.util.List;

/**
 * Created by Kristoffer on 2017-03-07.
 */
public interface PlayerLogDAO {
    List<PlayerLog> listGameLogs(int gameIdString);
}
