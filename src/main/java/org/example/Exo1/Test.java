package org.example.Exo1;

import org.example.Exo1.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.MetadataSources;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.getTransaction().begin();

        List<Product> productList = new ArrayList<>();

        for (Product product : productList
             ) { product.setPrice(10);
            product.setBrand("Carrefour");
            product.setStock(10);
            product.setReference("Test");
            product.setPurchaseDate();
            session.save(product);
            System.out.println(product.getId());
        }
        session.getTransaction().commit();

        session.load(Product.class, 2L);

        session.delete(productList.contains(3L));




        session.close();
        sessionFactory.close();




    }
}
