package com.logindemo.LoginDemo.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.logindemo.LoginDemo.model.User;

@Repository
public class UserDao {
	
	@PersistenceUnit(unitName = "Logindemo")
	private EntityManagerFactory emf;

	public void registerUser(User user) {
		EntityManager em =  emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			em.persist(user);
			transaction.commit();
		} 
		catch (Exception e) {
			transaction.rollback();
		}
	}

	public User checkUser(String username, String password) {
		try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<User> typedQuery = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
            typedQuery.setParameter("username", username);
            typedQuery.setParameter("password", password);

            return typedQuery.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
	}

}
