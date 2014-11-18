import DBLogic.SaveResult;
import DBLogic.winners;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: админ
 * Date: 18.11.14
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
public class test {

    public static void main (String[] args)
    {
        winners gameOpts = new winners("sdsfvs","ovvlolo","olovclo","ataccta");

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(gameOpts);

        session.getTransaction().commit();
    }

}
