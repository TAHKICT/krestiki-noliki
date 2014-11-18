package RunGame;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import GameOption.GameController;
import GameOption.GameOptions;
import GameOption.GamesSingleton;

/**
 * Created with IntelliJ IDEA.
 * User: админ
 * Date: 16.11.14
 * Time: 20:14
 * To change this template use File | Settings | File Templates.
 */
// user authentfication
public class AuthUser extends HttpServlet{

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {

            String user = request.getParameter("user");  //player name
            String url = request.getParameter("url");    // url of game page
            String triger = request.getParameter("create");  //is this a creating of game?
            HttpSession session = request.getSession();
            session.setAttribute("user", user);    //username = user
            session.setMaxInactiveInterval(30 * 60);
            Cookie userName = new Cookie("user", user);  //create a cookie of user
            userName.setMaxAge(30 * 60);
            response.addCookie(userName);
            if (triger != null)     // create game
            {
                String newUrl = MakeGame.MakeGameJSP.makeGame();   // create game
                Cookie cookieUrl = new Cookie("url", newUrl);      //create cookie URL
                response.addCookie(cookieUrl);
                response.sendRedirect(newUrl);
                GamesSingleton.getInstance().addGame(newUrl,user); //add new game to games storage
            }
            else
            {   System.out.println(url);
                if(!GamesSingleton.games.get(url).getSessionfull()) //if game session is not full
                {
                Cookie cookieUrl = new Cookie("url", url);           //add URK
                response.addCookie(cookieUrl);
                GamesSingleton.games.get(url).setPlayer2(user);      //add this player to the game
                response.sendRedirect(url);
                }
                else response.sendRedirect("error.html");            //error page if something wrong
            }

        }
    }

