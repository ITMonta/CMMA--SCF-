package TONICKXS.SCFT.services;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import TONICKXS.SCFT.entities.Machine;
import TONICKXS.SCFT.entities.Type;
import TONICKXS.SCFT.entities.Variante;
import TONICKXS.SCFT.utilities.GenericDAO;

/**
 * Session Bean implementation class VarianteServices
 */

public class VarianteServices  {
	
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
    public VarianteServices() {
      
    }

	
	public void delete2(int id) {		 
		  Variante b1=entityManager.find(Variante.class,id);
	        entityManager.remove(b1);
		
	}
	 public void add (Variante variante)
	   	{ 
			if(!entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().begin();
			}
			
	   		entityManager.persist(variante);
	   		entityManager.getTransaction().commit();
	   		entityManager.clear();	
	   	}
	   	
	   	public void update(Variante variante, int id)
	   	{
			if(!entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().begin();
			}
			
	   		Variante b1=entityManager.find(Variante.class,id);
		   	b1.setImage(variante.getImage());
		   	b1.setNom_variante(variante.getNom_variante());
		   	b1.setNumero(variante.getNumero());
		   	//b1.setOperations(variante.getOperations());
		   	b1.setPicture(variante.getPicture());
		   	b1.setVariante_module(variante.getVariante_module());
		   	
	   		entityManager.merge(b1);
	   		entityManager.getTransaction().commit();
	   		entityManager.clear();
	   		}
	   	public void delete(Variante variante)
	   	{
			if(!entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().begin();
			}
			
	   		entityManager.remove(variante);
	   		entityManager.getTransaction().commit();
	   		entityManager.clear();	
	   	}
	   	public List<Variante> findAll() {
	   		
	   		CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
	   		cq.select(cq.from(Variante.class));
	   		return entityManager.createQuery(cq).getResultList();
	   	}
	   	public Variante find(int entityID) {
			
			return entityManager.find(Variante.class, entityID);	
		}
   
}
