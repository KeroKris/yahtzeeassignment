package se.keroprog.webb.yahtzeebackend.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for views in the Yahtzee Game
 * Created by Kristoffer on 2017-03-02.
 */
@Controller
public class YahtzeePageController {

    @RequestMapping("/highscores")
    public String highscorePage(){
        return "highscore";
    }

    @RequestMapping("/gameLogView")
    public String gameLogPage(){
        return "gamelogview";
    }

    @RequestMapping("/playerLogView")
    public String playerLogPage(){
        return "playerlogview";
    }

    @RequestMapping("/gameView")
    public String gamePage(){
        return "gameview";
    }

    @RequestMapping("/gameOver")
    public String gameOverPage(){
        return "gameover";
    }
}
