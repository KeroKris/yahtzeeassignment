package se.keroprog.webb.yahtzeebackend.Repository.JDBCTemplates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import se.keroprog.webb.yahtzeebackend.Repository.PlayerLog;
import se.keroprog.webb.yahtzeebackend.Service.RowMappers.PlayerLogMapper;

import javax.sql.DataSource;
import java.util.List;

/**
 *
 * Created by Kristoffer on 2017-03-07.
 */
@Repository("playerLogDAO")
public class PlayerLogJDBCTemplate {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public List<PlayerLog> listPlayerLogs(String playerName){

        String SQL = "EXECUTE getGameSessionsForPlayer N'" + playerName.toLowerCase().trim() + "';";
        List<PlayerLog> playerLogList = jdbcTemplateObject.query(SQL, new PlayerLogMapper());

        return playerLogList;
    }

}
