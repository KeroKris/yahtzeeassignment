package se.keroprog.webb.yahtzeebackend.Service;

import se.keroprog.webb.yahtzeebackend.Repository.Player;

import java.util.List;

/**
 *
 * Created by Kristoffer on 2017-03-06.
 */
public class LoginService {

    static List<Player> playerList;

    public static List<Player> fillPlayerList(int numberOfPlayers){

        for (int i = 0; i < numberOfPlayers; i++) {
            playerList.add(new Player());
        }
        return playerList;
    }
}
