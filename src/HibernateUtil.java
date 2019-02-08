
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author iagocolodetti
 */
public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SESSION_FACTORY = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            System.err.println("Criação inicial do SessionFactory falhou.\n" + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
