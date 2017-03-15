package se.keroprog.webb.yahtzeebackend.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.keroprog.webb.yahtzeebackend.Repository.Player;
import se.keroprog.webb.yahtzeebackend.Service.LoginService;

import java.util.List;

/**
 * Created by Kristoffer on 2017-03-06.
 */
@RestController
@RequestMapping("/loginpage")
public class LoginController {

    private List<Player> playerList;

    @RequestMapping("/fill")
    public List<Player> fillPlayers(int numberOfPlayers){

        return LoginService.fillPlayerList(numberOfPlayers);
    }
}
