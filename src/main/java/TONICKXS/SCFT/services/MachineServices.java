package TONICKXS.SCFT.services;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

import TONICKXS.SCFT.entities.Machine;
import TONICKXS.SCFT.entities.User;
import TONICKXS.SCFT.utilities.GenericDAO;

/**
 * Session Bean implementation class MachineServices
 */

public class MachineServices  {

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
    public MachineServices() {
        
    }
    public void add (Machine machine)
	{
		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		
		entityManager.persist(machine);
		entityManager.getTransaction().commit();
		entityManager.clear();	
	}
	
	public void update(Machine machine)
	{
		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		
		entityManager.merge(machine);
		entityManager.getTransaction().commit();
		entityManager.clear();	
	}
	public void delete(Machine machine)
	{
		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		
		entityManager.remove(machine);
		entityManager.getTransaction().commit();
		entityManager.clear();	
	}
	public List<Machine> findAll() {
		entityManager.getTransaction().begin();
		CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Machine.class));
		return entityManager.createQuery(cq).getResultList();
	}
}
