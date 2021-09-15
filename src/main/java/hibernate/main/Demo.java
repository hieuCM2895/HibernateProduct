package hibernate.main;

import hibernate.model.Category;

import hibernate.model.Manufacturer;
import hibernate.model.Product;
import hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.sql.DatabaseMetaData;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Demo {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSesstionFactory().getCurrentSession();) {
//            session.getTransaction().begin();
//
//
//            session.getTransaction().commit();


//            session.getTransaction().commit();

//            CriteriaBuilder builder = session.getCriteriaBuilder();
//            CriteriaQuery<Product> query = builder.createQuery(Product.class);
//            Root<Product> root = query.from(Product.class);
//            query.select(root);

            // Filter by name
//            Predicate p1 = builder.like(root.get("name").as(String.class), "%iphone%");
//            Predicate p2 = builder.like(root.get("description").as(String.class), "%Mới 80%%");
//            Predicate p1 = builder.between(root.get("price").
//                    as(BigDecimal.class), new BigDecimal(9000000), new BigDecimal(15000000));
//            query.where(p1);
//
//            Query<Product> q = session.createQuery(query);
//            q.getResultList().forEach(System.out::println);

//            Product product = new Product();
//            product.setDescription("123");
//            product.setPrice(new BigDecimal(1000000));
//            product.setCategory(null);
//            product.setManufacturers(null);
//            product.setName("");
//            session.save(product);
//            Product iphone = session.get(Product.class, 3);
//
//            Manufacturer manufacturer = new Manufacturer();
//            manufacturer.setName("New generation 2020");
//            manufacturer.setCountry("Made in New York");
//
//
//            manufacturer.setProducts(iphone);
//            iphone.setManufacturers(manufacturer);
//
//            session.save(manufacturer);


//            Category c = session.get(Category.class, 2);
//            System.out.println(c);

//            List<Category> students = session.createQuery("from Category").list();
//            students.forEach(System.out::println);

//            Manufacturer manufacturer = session.get(Manufacturer.class, 8);
//            List<Product> pro = manufacturer.getProducts();
//            pro.forEach(c -> System.out.println(c.getName()));
//            statsDemo();
            session.getTransaction().begin();
            Product product = session.get(Product.class, 1);

            Product product1 = new Product();
            product1.setName("Iphone  13 Pro Max");
            product1.setDescription("Mới 100%");
            product1.setPrice(new BigDecimal(35000000));
            Category category = session.get(Category.class, 1);
            product1.setCategory(category);
            session.save(product1);


//            long start1 = System.currentTimeMillis();
//            for (int i = 0; i <= 2000; i++) {
//                Product product1 = new Product();
//                product1.setName("Iphone 11");
//                product1.setPrice(new BigDecimal(i));
//                product1.setDescription("Mới");
//                session.save(product1);
//
//                session.flush();
//                session.clear();
//
//            }
//            long finish1 = System.currentTimeMillis();



            session.getTransaction().commit();
            session.close();

//            System.out.println("Batch" + test1());

//
//            Session session2 = HibernateUtil.getSesstionFactory().getCurrentSession();
//            Product product2 = session2.get(Product.class, 2);
//            product2.setName("Iphone 12");
//            session2.save(product2);



//            product.setPrice(new BigDecimal(20000000));
//            product.setDescription("100% mới");
//            product.setCategory(session.get(Category.class, 1));
//
//            product.setManufacturers(session.get(Manufacturer.class, 8));
//            product.setManufacturers(session.get(Manufacturer.class, 10));
//
//            CriteriaBuilder builder = session.getCriteriaBuilder();
//            CriteriaQuery<Product> query = builder.createQuery(Product.class);
//            Root root = query.from(Product.class);
//            query = query.select(root);
//
//            Predicate p1 = builder.like(root.get("name").as(String.class), "%Samsung%");
//            Predicate p2 = builder.like(root.get("name").as(String.class), "%8%");
//            query = query.where(builder.or(p1, p2));
//            Query q = session.createQuery(query);
//
//            List<Product> products = q.getResultList();
//            products.forEach(System.out::println);

//            session.save(product);
//            session.getTransaction().commit();
//            session.close();
        }

//
    }

    public static long test1() {
        Session session1 = HibernateUtil.getSesstionFactory().openSession();
        session1.getTransaction().begin();
        long start = System.currentTimeMillis();
        for (int i = 2001; i <= 3001; i++) {
            Product product1 = new Product();
            product1.setName("Iphone 11");
            product1.setPrice(new BigDecimal(i));
            product1.setDescription("Mới");
            session1.save(product1);

            if (i % 200 == 0) {
                session1.flush();
                session1.clear();
            }
        }
        long finish = System.currentTimeMillis();
        session1.getTransaction().commit();
        session1.close();
        return finish - start;
    }

    public static void statsDemo() {
        try (Session session = HibernateUtil.getSesstionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
            Root<Product> root = query.from(Product.class);

            query.multiselect(builder.count(root.get("id")),
                    builder.max(root.get("price")).as(BigDecimal.class),
                    builder.avg(root.get("price")).as(BigDecimal.class));

            Query<Object[]> q = session.createQuery(query);
            Object[] r = q.getSingleResult();
            System.out.println("Số lượng sp: " + r[0]);
            System.out.println("Max price sp: " + r[1]);
            System.out.println("Trung bình sp: " + r[2]);

        }
    }
}
