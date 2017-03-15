package se.keroprog.webb.yahtzeebackend.Repository.JDBCTemplates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import se.keroprog.webb.yahtzeebackend.Repository.Highscore;
import se.keroprog.webb.yahtzeebackend.Service.HighscoreDAO;
import se.keroprog.webb.yahtzeebackend.Service.RowMappers.HighscoreMapper;

import javax.sql.DataSource;
import java.util.List;

/**
 *
 * Created by Kristoffer on 2017-02-27.
 */
@Repository("highscoreDAO")
public class HighscoreJDBCTemplate implements HighscoreDAO{

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Highscore> listHighscore() {
        String SQL = "SELECT TOP 10 p.Name, hs.Points\n" +
                "FROM PlayerRegister AS p\n" +
                "JOIN dbo.Highscore AS hs\n" +
                "ON p.id = hs.PlayerID\n" +
                "ORDER BY Points DESC";
        List<Highscore> highscores = jdbcTemplateObject.query(SQL, new HighscoreMapper());

        return highscores;
    }
}
