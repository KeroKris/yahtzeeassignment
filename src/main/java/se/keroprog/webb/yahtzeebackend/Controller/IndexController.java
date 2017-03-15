package se.keroprog.webb.yahtzeebackend.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kristoffer on 2017-02-27.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    String index(){
        return "index";
    }

    @RequestMapping("/login")
    String login(){
        return "selectplayers";
    }

    @RequestMapping("/playerinfo")
    String playerInfoPage(){
        return "loginpage";
    }
}
