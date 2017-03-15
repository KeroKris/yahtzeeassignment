package se.keroprog.webb.yahtzeebackend.Repository.JDBCTemplates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import se.keroprog.webb.yahtzeebackend.Repository.GameLog;
import se.keroprog.webb.yahtzeebackend.Repository.Player;
import se.keroprog.webb.yahtzeebackend.Service.GameLogDAO;
import se.keroprog.webb.yahtzeebackend.Service.RowMappers.GameLogMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * Created by Kristoffer on 2017-03-07.
 */
@Repository("gameLogDAO")
public class GameLogJDBCTemplate implements GameLogDAO{

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public List<GameLog> listGameLogs(int gameIdString) {
        String SQL = "SELECT gameRound.RoundNumber, player.name, gameRound.Result, gameRound.Combination, gameRound.Points\n" +
                "FROM GameRounds AS gameRound\n" +
                "JOIN PlayerRegister AS player\n" +
                "ON gameRound.PlayerID = player.id\n" +
                "WHERE gameRound.GameId = " + gameIdString;

        List<GameLog> gameLogList = jdbcTemplateObject.query(SQL, new GameLogMapper());

        return gameLogList;
    }

    @Override
    public long insertGameSession(){
        final PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                final PreparedStatement ps = connection.prepareStatement("INSERT INTO GameSession (WinnerPoints) VALUES (0)", Statement.RETURN_GENERATED_KEYS);
                return ps;
            }
        };

        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplateObject.update(psc, keyHolder);
        final long newGameLogID = keyHolder.getKey().longValue();

        return newGameLogID;
    }

    @Override
    public void logRound(int gameID, int currentRound, int playerID, String diceString, String setString, int score) {
        String SQL = "InsertNewRound " + gameID + ", "+ currentRound + ", " + playerID
                + ", '" + diceString + "', '" + setString + "', " + score;
        jdbcTemplateObject.execute(SQL);
    }

    @Override
    public void updateGameLog(int gameID, List<Player> players) {

        int ID = players.get(0).getId();
        int winnerScore = players.get(0).getScore();
        String SQL = "UpdateGameSession " + gameID + ", " + ID + ", " + winnerScore;

        for (int i = 1; i < players.size(); i++) {
            SQL += ", " + players.get(i).getId();
        }

        jdbcTemplateObject.execute(SQL);

        //saves scores to highscore
        for (Player player : players) {
            SQL = "InsertScoreToHighscore " + player.getId() + ", " + player.getScore();

            jdbcTemplateObject.execute(SQL);
            System.out.println(SQL);
        }
    }
}
