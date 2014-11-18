package GameOption;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: админ
 * Date: 17.11.14
 * Time: 0:57
 * To change this template use File | Settings | File Templates.
 */
//to be sure that we have only one game with two players
public class GamesSingleton {
    private static GamesSingleton instance = new GamesSingleton();   //one class object

    public static Map<String, GameOptions> games = new HashMap<String, GameOptions>(); //games storage massive

    public void addGame(String url, String firstPlayer )   //add game to storage
    {
        games.put(url, new GameOptions(url, firstPlayer));
    }

    private GamesSingleton()
    {}
    public static GamesSingleton getInstance()
    {
        return instance;   //get GameSingleTone object
    }


}
