package se.keroprog.webb.yahtzeebackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import se.keroprog.webb.yahtzeebackend.Repository.*;
import se.keroprog.webb.yahtzeebackend.Repository.JDBCTemplates.GameLogJDBCTemplate;
import se.keroprog.webb.yahtzeebackend.Repository.JDBCTemplates.HighscoreJDBCTemplate;
import se.keroprog.webb.yahtzeebackend.Repository.JDBCTemplates.PlayerLogJDBCTemplate;

import java.util.List;

/**
 *
 * Created by Kristoffer on 2017-02-26.
 */
@RestController
public class DatabaseLogViewController {

    @Autowired
    @Qualifier("highscoreDAO")
    private HighscoreJDBCTemplate highscoreJDBCTemplate;

    @Autowired
    @Qualifier("gameLogDAO")
    private GameLogJDBCTemplate gameLogJDBCTemplate;

    @Autowired
    @Qualifier("playerLogDAO")
    private PlayerLogJDBCTemplate playerLogJDBCTemplate;

    @RequestMapping(value = "/highscore", method = RequestMethod.GET)
    public List<Highscore> listHighscores(){

        return highscoreJDBCTemplate.listHighscore();
    }

    @RequestMapping(value = "/log/{gameID}", method = RequestMethod.GET)
    public List<GameLog> listGameLog(@PathVariable int gameID){

        return gameLogJDBCTemplate.listGameLogs(gameID);
    }

    @RequestMapping(value = "/playerlog/{playerName}", method = RequestMethod.GET)
    public List<PlayerLog> listPlayerLog(@PathVariable String playerName){
        return playerLogJDBCTemplate.listPlayerLogs(playerName);
    }

    @RequestMapping(value = "/registergamesession", method = RequestMethod.GET)
    public long insertGameSession(){
        return gameLogJDBCTemplate.insertGameSession();
    }

    @RequestMapping(value = "/updateGameLog", method = RequestMethod.POST, consumes = "application/json")
    public void logGame(@RequestBody List<Player> players){

        int gameID = players.get(0).getGameID();

        gameLogJDBCTemplate.updateGameLog(gameID, players);
    }
}
