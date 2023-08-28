package org.example.Exo1;

import org.example.Exo1.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.MetadataSources;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.getTransaction().begin();

        List<Product> productList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.setPrice(5);
            product.setBrand("Carrefour");
            product.setStock(10);
            product.setReference("Steak");
            String date = "10/08/2023";
            LocalDate pDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            product.setPurchaseDate(pDate);
            productList.add(product);
            session.save(product);
            System.out.println(product.getId());
        }
        session.getTransaction().commit();


        session.getTransaction().begin();
        Product product = session.load(Product.class, 2L);
        session.update(product);

        session.getTransaction().begin();
        session.delete(productList.contains(3L));
        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
    }
}
