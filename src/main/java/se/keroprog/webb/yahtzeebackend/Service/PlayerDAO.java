package se.keroprog.webb.yahtzeebackend.Service;

import se.keroprog.webb.yahtzeebackend.Repository.Player;

import java.util.List;

/**
 * Created by Kristoffer on 2017-03-10.
 */
public interface PlayerDAO {

    List<Player> listPlayers(String name);

    void addPlayerToDatabase(String name, String s);

    Player validatePassword(String name, String s);
}
