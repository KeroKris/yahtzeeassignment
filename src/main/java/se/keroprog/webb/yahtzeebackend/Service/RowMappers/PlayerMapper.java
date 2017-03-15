package se.keroprog.webb.yahtzeebackend.Service.RowMappers;

import org.springframework.jdbc.core.RowMapper;
import se.keroprog.webb.yahtzeebackend.Repository.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Kristoffer on 2017-03-10.
 */
public class PlayerMapper implements RowMapper<Player>{
    @Override
    public Player mapRow(ResultSet resultSet, int i) throws SQLException {
        Player player = new Player();
        if (resultSet != null){
            System.out.println("PlayerMapper running...");
            player.setId(resultSet.getInt(1));
            System.out.println("Id: " + player.getId());
        }

        return player;
    }
}
