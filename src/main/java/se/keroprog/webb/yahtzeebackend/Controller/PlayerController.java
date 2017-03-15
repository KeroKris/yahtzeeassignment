package se.keroprog.webb.yahtzeebackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import se.keroprog.webb.yahtzeebackend.Repository.Player;
import se.keroprog.webb.yahtzeebackend.Repository.JDBCTemplates.PlayerJDBCTemplate;

import java.util.List;

/**
 *
 * Created by Kristoffer on 2017-03-10.
 */
@RestController
@RequestMapping("/player")
public class PlayerController {


    @Autowired
    @Qualifier("playerDAO")
    private PlayerJDBCTemplate playerJDBCTemplate;


    @RequestMapping(value = "/validate", method = RequestMethod.POST, consumes = "application/json")
    public Player validatePlayer(@RequestBody Player player){

        List<Player> players = playerJDBCTemplate.listPlayers(player.getName());

        if (players.size() == 0){
            playerJDBCTemplate.addPlayerToDatabase(player.getName(), player.getPassword());
            players = playerJDBCTemplate.listPlayers(player.getName());
        } else {
            if (playerJDBCTemplate.validatePassword(player.getName(), player.getPassword()).getId() == -1){
                player = new Player();
                player.setId(-1);
                player.setName("Error Wrong Password");
                player.setScore(0);
                return player;
            }
        }

        player.setId(players.get(0).getId());
        System.out.println("Player ID: " + player.getId() + ", Name: " + player.getName());
        player.setScore(0);

        return player;
    }

}
