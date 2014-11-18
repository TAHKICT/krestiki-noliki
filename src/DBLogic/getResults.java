package DBLogic;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: админ
 * Date: 18.11.14
 * Time: 15:31
 * To change this template use File | Settings | File Templates.
 */
//this class get all play history from database uses hibernate
public class getResults {
    public List<winners> get(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<winners> users = session.createCriteria(winners.class)
                .list();

        session.getTransaction().commit();
        return users;
    }
}
