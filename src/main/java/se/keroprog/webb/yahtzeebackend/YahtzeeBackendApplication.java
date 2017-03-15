package se.keroprog.webb.yahtzeebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class YahtzeeBackendApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(YahtzeeBackendApplication.class, args);

//		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
//
//		HighscoreJDBCTemplate highscoreJDBCTemplate = (HighscoreJDBCTemplate) context.getBean("highscoreJDBCTemplate");
//
//		System.out.println("Listing Highscore");
//
//		List<Highscore> highscores = highscoreJDBCTemplate.listHighscore();
//
//		for (Highscore hs : highscores) {
//			System.out.println(hs.getName() + "\t" + hs.getScore());
//		}


//		SpringApplication.run(YahtzeeBackendApplication.class, args);
	}
}
