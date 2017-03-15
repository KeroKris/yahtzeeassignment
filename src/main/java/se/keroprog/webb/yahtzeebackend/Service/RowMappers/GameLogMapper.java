package se.keroprog.webb.yahtzeebackend.Service.RowMappers;

import org.springframework.jdbc.core.RowMapper;
import se.keroprog.webb.yahtzeebackend.Repository.GameLog;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * Created by Kristoffer on 2017-03-07.
 */
public class GameLogMapper implements RowMapper<GameLog>{
    @Override
    public GameLog mapRow(ResultSet resultSet, int i) throws SQLException {
        GameLog gameLog = new GameLog();

        gameLog.setRound(resultSet.getString(1));
        gameLog.setPlayer(resultSet.getString(2));
        gameLog.setResult(resultSet.getString(3));
        gameLog.setCombination(resultSet.getString(4));
        gameLog.setPoints(resultSet.getString(5));

        return gameLog;
    }
}
