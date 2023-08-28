package org.example.Exo1;

import org.example.Exo1.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.getTransaction().begin();

        List<Product> productList = new ArrayList<>();

//        for (int i = 0; i < 5; i++) {
//            Product product = new Product();
//            product.setPrice(5);
//            product.setBrand("Carrefour");
//            product.setStock(10);
//            product.setReference("Steak");
//            String date = "10.08.2023";
//            LocalDate pDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
//            product.setPurchaseDate(pDate);
//            productList.add(product);
//            session.save(product);
//            System.out.println(product.getId());
//        }
//        session.getTransaction().commit();
//
//
//        session.getTransaction().begin();
//        Product product = session.load(Product.class, 2L);
//        session.update(product);
//
//        session.getTransaction().begin();
//        session.delete(productList.contains(3L));
//        session.getTransaction().commit();
//
//        session.close();
//        sessionFactory.close();
        //====================Exo1============================

        //====================exo 2===========================
        //Afficher tous les produits
        Query<Product> productQuery = session.createQuery("from Product");


        Iterator<Product> productIterator = productQuery.iterate();
        while (productIterator.hasNext()){
            Product p = (Product) productIterator.next();
            System.out.println(p.getReference()); //todo toString Product
        }

        //Afficher les produits supérieurs à 100 euros


        Query<Product> productQuery1 = session.createQuery("from Product where price  < 100");

        Iterator<Product> productIterator1 = productQuery1.iterate();
        while (productIterator1.hasNext()){
            Product p1 = (Product) productIterator1.next();
            System.out.println(p1.getBrand());
        }

        //Afficher les produits achetés entre deux dates
        String maxDate = "11.08.2023";
        LocalDate maxDateFormat = LocalDate.parse(maxDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String minDate = "09.08.2023";
        LocalDate minDateFormat = LocalDate.parse(minDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        Query<Product> productQuery2 = session.createQuery("from Product where purchaseDate < ?0 AND purchaseDate > ?1");
        productQuery2.setParameter(0, maxDateFormat);
        productQuery2.setParameter(1, minDateFormat);

        Iterator<Product> productIterator3 = productQuery2.iterate();
        while (productIterator3.hasNext()){
            Product p2 = (Product) productIterator3.next();
            System.out.println(p2.getReference());
        }

        //====================fin exo 2=======================
    }
}
