package se.keroprog.webb.yahtzeebackend.Service;

import org.springframework.stereotype.Repository;
import se.keroprog.webb.yahtzeebackend.Repository.Die;

/**
 *
 * Created by Kristoffer on 2017-03-13.
 */
@Repository("DiceDAO")
public class DiceService implements DiceDAO{



    public static Die[] listDice(String diceString) {

        Die[] dice ={new Die(Integer.parseInt(Character.toString(diceString.charAt(0)))),
                new Die(Integer.parseInt(Character.toString(diceString.charAt(1)))),
                new Die(Integer.parseInt(Character.toString(diceString.charAt(2)))),
                new Die(Integer.parseInt(Character.toString(diceString.charAt(3)))),
                new Die(Integer.parseInt(Character.toString(diceString.charAt(4))))};

        return bubbleSortDice(dice);
    }

    private static Die[] bubbleSortDice(Die[] dice) {
        boolean swapped = true;
        int i, j = dice.length;
        Die tmp;
        while(swapped){
            swapped = false;
            i = 0;
            while(i < j - 1){
                if(dice[i].getDieResult().getValue() > dice[i+1].getDieResult().getValue()){
                    tmp = dice[i+1];
                    dice[i+1] = dice[i];
                    dice[i] = tmp;
                    swapped = true;
                }
                i++;
            }
            j--;
        }
        return dice;
    }
}
