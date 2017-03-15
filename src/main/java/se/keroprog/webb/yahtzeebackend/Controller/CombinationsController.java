package se.keroprog.webb.yahtzeebackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import se.keroprog.webb.yahtzeebackend.Repository.Die;
import se.keroprog.webb.yahtzeebackend.Repository.JDBCTemplates.GameLogJDBCTemplate;
import se.keroprog.webb.yahtzeebackend.Repository.Player;
import se.keroprog.webb.yahtzeebackend.Service.DiceService;
import se.keroprog.webb.yahtzeebackend.Service.YahtzeeSet;

/**
 * Controller for evaluating the score of a Yahtzee Set and loggin the round to a database.
 * Created by Kristoffer on 2017-03-13.
 */
@RestController
@RequestMapping("/combination")
public class CombinationsController {

    private Die[] dice = new Die[5];


    @Autowired
    @Qualifier("gameLogDAO")
    private GameLogJDBCTemplate gameLogJDBCTemplate;

    @RequestMapping(value = "/score/{combinationID}", method = RequestMethod.POST, consumes = "application/json")
    public int scoreCombination(@PathVariable String combinationID, @RequestBody Player player){


        dice = DiceService.listDice(player.getDiceString());

        YahtzeeSet set = YahtzeeSet.getSetWithIdentifier(Integer.parseInt(combinationID));

        int gameID = player.getGameID();
        int currentRound = player.getCurrentRound();
        int playerID = player.getId();
        int score = set.evaluateSet(dice);
        String diceString = player.getDiceString();
        String setString = set.toString();

        for (Die d:dice) {
            System.out.println(d.getDieResult());
        }

        gameLogJDBCTemplate.logRound(gameID, currentRound, playerID, diceString, setString, score);

        return score;
    }
}
