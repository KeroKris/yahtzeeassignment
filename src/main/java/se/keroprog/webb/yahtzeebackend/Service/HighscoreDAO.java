package se.keroprog.webb.yahtzeebackend.Service;

import se.keroprog.webb.yahtzeebackend.Repository.Highscore;

import java.util.List;

/**
 * Created by Kristoffer on 2017-02-27.
 */
public interface HighscoreDAO {

    List<Highscore> listHighscore();
}
