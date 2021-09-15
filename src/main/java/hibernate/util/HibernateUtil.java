package hibernate.util;

import hibernate.model.Category;
import hibernate.model.Manufacturer;
import hibernate.model.Product;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private final static SessionFactory FACTORY;

    // Khối khởi động tĩnh chạy 1 lần duy nhất khi lớp này được nạp lần đầu tiên
    static {
        Configuration conf = new Configuration();
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/TEST");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "matkhau1995");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "update");


        conf.setProperties(properties);
        conf.addAnnotatedClass(Category.class);
        conf.addAnnotatedClass(Product.class);
        conf.addAnnotatedClass(Manufacturer.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();

        FACTORY = conf.buildSessionFactory(registry);
    }

    public static SessionFactory getSesstionFactory() {
        return FACTORY;
    }

    public static void closeFactory() {
        if (FACTORY != null) {
            try {
                FACTORY.close();
            } catch (HibernateException ignored) {
               ignored.printStackTrace();
            }
        }
    }
}
