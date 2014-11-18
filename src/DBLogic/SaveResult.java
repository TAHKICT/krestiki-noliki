package DBLogic;
import GameOption.GameOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class SaveResult {

        public void save(GameOptions game) {

            winners gameOpts = new winners(game.getGameURL(), game.getWinner(),game.getPlayer1(), game.getPlayer2());

            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.save(gameOpts);

            session.getTransaction().commit();

        }


}
