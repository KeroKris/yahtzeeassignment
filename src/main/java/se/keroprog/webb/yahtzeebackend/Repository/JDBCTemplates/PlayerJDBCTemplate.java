package se.keroprog.webb.yahtzeebackend.Repository.JDBCTemplates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import se.keroprog.webb.yahtzeebackend.Repository.Player;
import se.keroprog.webb.yahtzeebackend.Service.PlayerDAO;
import se.keroprog.webb.yahtzeebackend.Service.RowMappers.PWMapper;
import se.keroprog.webb.yahtzeebackend.Service.RowMappers.PlayerMapper;

import javax.sql.DataSource;
import java.util.List;

/**
 *
 * Created by Kristoffer on 2017-03-10.
 */
@Repository("playerDAO")
public class PlayerJDBCTemplate implements PlayerDAO {

    private static JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Player> listPlayers(String name) {
        String SQL = "SELECT id FROM PlayerRegister WHERE name = '" + name + "'";

        return jdbcTemplate.query(SQL, new PlayerMapper());

    }

    @Override
    public void addPlayerToDatabase(String name, String s) {

        String SQL = "INSERT INTO PlayerRegister (name, identificatorQuery)\n" +
                "VALUES ('" + name + "', '" + s + "')";

        jdbcTemplate.execute(SQL);
    }

    @Override
    public Player validatePassword(String name, String s) {

        String SQL = "SELECT PlayerRegister.name FROM PlayerRegister " +
                "WHERE name = '" + name + "' AND identificatorQuery = '" + s + "'";

        Player player = new Player();

        if (jdbcTemplate.query(SQL, new PWMapper()).size() == 0){
            System.out.println("Password checker returned empty list");
            player.setId(-1);
        }
        return player;
    }
}
