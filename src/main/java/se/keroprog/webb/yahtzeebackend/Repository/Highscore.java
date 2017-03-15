package se.keroprog.webb.yahtzeebackend.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Kristoffer on 2017-02-27.
 */
@Entity
public class Highscore {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private int rank;

    private String name, score;

    public Highscore() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public int getId() {
        return id;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
