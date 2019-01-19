package TONICKXS.SCFT.services;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

import TONICKXS.SCFT.entities.Machine;
import TONICKXS.SCFT.entities.Module;
import TONICKXS.SCFT.entities.Operation;
import TONICKXS.SCFT.utilities.GenericDAO;

/**
 * Session Bean implementation class OperationServices
 */

public class OperationServices   {

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
    public OperationServices() {
     
    }
    public void add (Operation operation)
   	{
		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		
   		entityManager.persist(operation);
   		entityManager.getTransaction().commit();
   		entityManager.clear();	
   	}
   	
   	public void update(Operation operation)
   	{
		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		
   		entityManager.merge(operation);
   		entityManager.getTransaction().commit();
   		entityManager.clear();	
   	}
   	public void delete(Operation operation)
   	{
		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		
   		Operation o=entityManager.find(Operation.class, operation.getIdOperation());
   		entityManager.remove(o);
   		entityManager.getTransaction().commit();
   		entityManager.clear();	
   	}
   	public List<Operation> findAll() {
		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		
   		CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
   		cq.select(cq.from(Operation.class));
   		return entityManager.createQuery(cq).getResultList();
   	}


}
