package TONICKXS.SCFT.services;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import TONICKXS.SCFT.entities.User;
import TONICKXS.SCFT.utilities.GenericDAO;

/**
 * Session Bean implementation class UserService
 */

public class UserService   {
	/**
	 * Default constructor.
	 */
	public UserService() {
	
	}
	private static final EntityManagerFactory emFactoryObj;
	    private static final String PERSISTENCE_UNIT_NAME = "SCF";  
	    static {
	        emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    }
	  // This Method Is Used To Retrieve The 'EntityManager' Object
	    public static EntityManager getEntityManager() {
	        return emFactoryObj.createEntityManager();
	    }

	    public 	EntityManager entityManager = getEntityManager();
	public User login(String login, String password) {
		User user = null;
			Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login=:l AND u.password=:p", User.class);
			 query.setParameter("l", login);
			 query.setParameter("p", password); 
			 user = (User) query.getSingleResult();
		return user;
	}
	public void add (User user)
	{
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		entityManager.clear();	
	}
	
	public void update(User user)
	{
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
		entityManager.clear();	
	}
	public void delete(User user)
	{
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
		entityManager.clear();	
	}
	public List<User> findAll() {
		entityManager.getTransaction().begin();
		CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
		cq.select(cq.from(User.class));
		return entityManager.createQuery(cq).getResultList();
	}
}
