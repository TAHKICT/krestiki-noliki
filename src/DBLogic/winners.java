package DBLogic;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: админ
 * Date: 16.11.14
 * Time: 14:47
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "winners")
public class winners {
    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    private Integer id;
    private String gameURL;
    private String winner;
    private String player1;
    private String player2;

    public winners(){}

    public winners(String gameURL,String winner, String player1, String player2){
        this.gameURL = gameURL;
        this.winner = winner;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @Column(name="id")
    public Integer getId() {
        return id;

    }
    @Column(name = "gameURL")
    public String getGameURL() {
        return gameURL;
    }
    @Column(name = "winner")
    public String getWinner() {
        return winner;
    }
    @Column(name = "player1")
    public String getPlayer1() {
        return player1;
    }
    @Column(name = "player2")
    public String getPlayer2() {
        return player2;
    }
    public void setGameURL(String gameURL){
        this.gameURL = gameURL;
    }
    public void setWinner(String winner){
        this.winner = winner;
    }
    public void setPlayer1(String player1){
        this.player1 = player1;
    }
    public void setPlayer2(String player2){
        this.player2 = player2;
    }
}
