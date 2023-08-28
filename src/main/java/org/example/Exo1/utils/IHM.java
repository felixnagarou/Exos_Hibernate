package org.example.Exo1.utils;

import org.example.Exo1.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class IHM {
    public static Scanner scanner = new Scanner(System.in);
    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    Session session = sessionFactory.openSession();


    public List<Product> displayProductsBoughtBetweenTwoGivenDates(LocalDate maxDate, LocalDate minDate) {
        maxDate = givenDateFormatter(scanner.nextLine());
        minDate = givenDateFormatter(scanner.nextLine());
        List<Product> productList = new ArrayList<>();
        Query<Product> productQuery = session.createQuery("from Product where purchaseDate < ?0 AND purchaseDate > ?1");
        productQuery.setParameter(0, maxDate);
        productQuery.setParameter(1, minDate);

        Iterator<Product> productIterator = productQuery.iterate();
        while (productIterator.hasNext()) {
            Product p = (Product) productIterator.next();
            System.out.println(p.toString());
        }
            return productList;
        }

        public List<Product> displayProductsGivenMaxPriceThreshold (double price){
            price = scanner.nextDouble();
            List<Product> productList = new ArrayList<>();

            return productList;

        }

        public LocalDate givenDateFormatter (String date){
            LocalDate formatedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyy"));
            return formatedDate;
        }
    }

