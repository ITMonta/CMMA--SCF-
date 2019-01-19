package TONICKXS.SCFT.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

import TONICKXS.SCFT.entities.Machine;
import TONICKXS.SCFT.entities.Module;
import TONICKXS.SCFT.entities.Operation;
import TONICKXS.SCFT.entities.Type;
import TONICKXS.SCFT.utilities.GenericDAO;

/**
 * Session Bean implementation class TypeServices
 */

public class TypeServices   {

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
    public TypeServices() {
        
    }
    public void add (Type type)
   	{
		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		
   		entityManager.persist(type);
   		entityManager.getTransaction().commit();
   		entityManager.clear();	
   	}
   	
   	public void update(Type type,int id)
   	{
		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		
   		Type existingType=entityManager.find(Type.class, id);
   		existingType.setGammes(type.getGammes());
   		//existingType.setModules(type.getModules());
   		existingType.setNom_Type(type.getNom_Type());
   		
   		entityManager.merge(existingType);
   		entityManager.getTransaction().commit();
   		entityManager.clear();	
   	}
   	public void delete(Type type)
   	{
		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		
   		Type t=entityManager.find(Type.class, type.getIdType());
   		
   		entityManager.remove(t);
   		entityManager.getTransaction().commit();
   		entityManager.clear();	
   	}
   	public List<Type> findAll() {
		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		
   		CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
   		cq.select(cq.from(Type.class));
   		return entityManager.createQuery(cq).getResultList();
   	}

	

}
