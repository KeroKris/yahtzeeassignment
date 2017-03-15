package se.keroprog.webb.yahtzeebackend.Service.RowMappers;

import org.springframework.jdbc.core.RowMapper;
import se.keroprog.webb.yahtzeebackend.Repository.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Kristoffer on 2017-03-10.
 */
public class PWMapper implements RowMapper<Player> {
    @Override
    public Player mapRow(ResultSet resultSet, int i) throws SQLException {
        Player player = new Player();

        if (resultSet != null){
            System.out.println("PasswordMapper running...");
            System.out.println("Password Correct!");

        }else {
            player.setId(-1);
        }


        return player;
    }
}
