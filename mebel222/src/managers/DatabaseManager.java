/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Buyer;
import entity.Order;
import entity.Product;
import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author rinhu
 */
public class DatabaseManager {
    private EntityManagerFactory emf;
    private EntityManager em;

    public DatabaseManager() {
        emf = Persistence.createEntityManagerFactory("SofaStoreDatabasePU");
        em = emf.createEntityManager();
    }

    public void closeEntityManager() {
        if (em != null && em.isOpen()) {
            em.close();
        }

        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
    public void saveProduct(Product product) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            if (product.getId() == null) {
                em.persist(product);
            } else {
                em.merge(product);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } 
    }
    
    public List<Product> getProducts() {
        return em.createQuery("SELECT product FROM Product product").getResultList();
    }

    public void saveBuyer(Buyer buyer) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            if (buyer.getId() == null) {
                em.persist(buyer);
            } else {
                em.merge(buyer);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } 
    }
    
    public List<User> getUsers(){
        return em.createQuery("SELECT user FROM User user").getResultList();
    }
    
    public User getUserById(Long id){
        return (User) em.createQuery("SELECT * FROM user WHERE id = "+id).getSingleResult();
    }

    public void saveUser(User user) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            if (user.getId() == null) {
                em.persist(user);
            } else {
                em.merge(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } 
    }
    
    public Product getProductById(Long id){
        return (Product) em.createQuery("SELECT product FROM Product product WHERE id = "+id).getSingleResult();
    }
    
    public Product getProduct(Long id) {
        try {
            return em.find(Product.class, id);
        } catch (Exception e) {
            return null;
        }
    }
    public void saveOrder(Order order) {
        try {
            em.getTransaction().begin();
            if(order.getId() == null){
                em.persist(order);
            }else{
                em.merge(order);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public List<Order> getListOrders() {
        return em.createQuery("SELECT order FROM Order order").getResultList();
    }

}
