package GameOption;
import DBLogic.SaveResult;
import GameOption.GameOptions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Created with IntelliJ IDEA.
 * User: админ
 * Date: 17.11.14
 * Time: 0:39
 * To change this template use File | Settings | File Templates.
 */
//This is Game Controller server servlet!
public class GameController extends HttpServlet {
        //this is response, when player makes a move
        private static void returnDoMove(String playerName, int kord1,int kord2,HttpServletResponse response, String url) throws IOException {
            GameOptions game = GamesSingleton.games.get(url);
            game.MakeStep(playerName,kord1,kord2);
            response.sendRedirect(url);

        }
         //this is response, when player can't makes a move
        private static void returnNotMove(HttpServletResponse response, String url) throws IOException {
            try {
                response.sendRedirect(url);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
         //redefinition of "doGet" method
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            //System.out.println("Game controller is working now");
            String sessionUrl = request.getParameter("sessionUrl");
            String playerName = request.getParameter("name");
            int kor1 = -1;     //  null parameters if player can't
            int kor2 = -1;     //   move

            if (request.getParameter("kor1") != null && request.getParameter("kor2") != null)
            {
                kor1 = Integer.parseInt(request.getParameter("kor1"));    //get x-coordinates
                kor2 = Integer.parseInt(request.getParameter("kor2"));    //get y-coordinates
            }
            //litle cheking for console
           // System.out.println("Current player: "+playerName + " ,game URL: " + sessionUrl);
           // System.out.println("Player #1: "+GamesSingleton.games.get(sessionUrl).getPlayer1());
            //System.out.println("Player #2: "+GamesSingleton.games.get(sessionUrl).getPlayer2());
            //System.out.println("--------------------------------------");
            //if game page exist - let's play!
            if (GamesSingleton.games.get(sessionUrl) != null)
            {
                //System.out.println("Session full: "+GamesSingleton.games.get(sessionUrl).getSessionfull());
                //System.out.println("___________________________________");
                //Get necessary game object
                GameOptions game = GamesSingleton.games.get(sessionUrl);
                if (game.gameOver())
                    response.sendRedirect("draw.html");
                else
                {
                    game.CanMakeStep(0,0);
                    boolean myturn = true;
                    if (game.getWinner() != null)     //if winner exists
                    {
                        SaveResult saveResult = new SaveResult();

                        if (playerName.equals(game.getWinner())){
                           // saveResult.save(game);
                            saveResult.save(game);
                            response.sendRedirect("winner.html");  //if this player is winner - redirect to winner page
                        }
                        else{
                            //saveResult.save(game);
                            response.sendRedirect("looser.html");  //if this player is looser - redirect to looser page
                        }
                    }
                    else   //if winner don't exist
                    {
                        //Decide whose move. Player 1 - true
                        if (playerName.equals(game.getPlayer1()))
                        {
                            myturn = true;
                        }
                        //Player 2 - false
                        else if (playerName.equals(game.getPlayer2()))
                            myturn = false;
                        //if not true and not false - error
                        else
                            response.sendRedirect("error.html");
                        // decide what to do, if it move of player, whose requested page, let him move, if not - don't let.
                        if (game.getTurn() == myturn)  //if it's turn of this player do....
                        {
                            if (kor1 == -1 || kor2 == -1 || !game.CanMakeStep(kor1,kor2))  //cheking about redefinition of coordinates
                            {
                                game.setCurrentPlayerName(playerName);   //if not player dont type coordinate =>
                                response.sendRedirect(sessionUrl);       //=>redirect to game page
                            }
                            else
                            {
                                if (playerName.equals(game.getPlayer1()))  //if it's player1 =>
                                    game.setCurrentPlayerName(game.getPlayer2());//=> next player will be player2
                                else                                              //or
                                    game.setCurrentPlayerName(game.getPlayer1()); //it's player2 => next player will be player1
                                returnDoMove(playerName,kor1, kor2, response, sessionUrl); //if coordinates are ok => do move
                            }
                        }
                        else     //if it's turn of another player =>
                        {
                            returnNotMove(response, sessionUrl); //=>our response = "you can't move"
                        }
                    }
                }
            }
            //if game page not exist ==>>
            else
            {
                response.sendRedirect("error.html"); //==>> go to error page
            }

        }
        //if there is "doPost" reqest from a player => make redirect to "doGet" request
        protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
         {
            doGet(request,response);
         }
    }

