package se.keroprog.webb.yahtzeebackend.Service.RowMappers;

import org.springframework.jdbc.core.RowMapper;
import se.keroprog.webb.yahtzeebackend.Repository.Highscore;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * Created by Kristoffer on 2017-02-27.
 */
public class HighscoreMapper implements RowMapper<Highscore>{

    @Override
    public Highscore mapRow(ResultSet resultSet, int i) throws SQLException {
        Highscore highscore = new Highscore();
        highscore.setName(resultSet.getString(1));
        highscore.setScore(resultSet.getString(2));
        highscore.setRank(i+1);

        return highscore;
    }
}
