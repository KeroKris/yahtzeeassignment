package se.keroprog.webb.yahtzeebackend.Service.RowMappers;

import org.springframework.jdbc.core.RowMapper;
import se.keroprog.webb.yahtzeebackend.Repository.PlayerLog;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Kristoffer on 2017-03-07.
 */
public class PlayerLogMapper implements RowMapper<PlayerLog>{
    @Override
    public PlayerLog mapRow(ResultSet resultSet, int i) throws SQLException {
        PlayerLog playerLog = new PlayerLog();

        playerLog.setGameID(resultSet.getString(1));
        playerLog.setWinner(resultSet.getString(2));
        playerLog.setWinnerPoints(resultSet.getString(3));
        playerLog.setSecondPlace(resultSet.getString(4));
        playerLog.setThirdPlace(resultSet.getString(5));
        playerLog.setFourthPlace(resultSet.getString(6));
        playerLog.setDate(resultSet.getString(7));

        System.out.println(playerLog.getWinner());

        return playerLog;
    }
}
