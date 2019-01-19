package TONICKXS.SCFT.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

import TONICKXS.SCFT.entities.Gamme;
import TONICKXS.SCFT.entities.Machine;
import TONICKXS.SCFT.entities.Type;
import TONICKXS.SCFT.entities.Variante;
import TONICKXS.SCFT.utilities.GenericDAO;

/**
 * Session Bean implementation class GammeServices
 */

public class GammeServices {
	private static final EntityManagerFactory emFactoryObj;
	private static final String PERSISTENCE_UNIT_NAME = "SCF";
	static {
		emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	// This Method Is Used To Retrieve The 'EntityManager' Object
	public static EntityManager getEntityManager() {
		return emFactoryObj.createEntityManager();
	}

	public EntityManager entityManager = getEntityManager();

	/**
	 * Default constructor.
	 */
	public GammeServices() {

	}

	public void add(Gamme gamme) {
		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		
		entityManager.persist(gamme);
		entityManager.getTransaction().commit();
		entityManager.clear();
	}

	public void update(Gamme gamme) {

		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		
		Gamme existingType = entityManager.find(Gamme.class, gamme.getIdGamme());
		existingType.setVariantes(gamme.getVariantes());
		existingType.setC_designurl(gamme.getC_designurl());
		existingType.setNom_Gamme(gamme.getNom_Gamme());
		existingType.setTemps(gamme.getTemps());
		existingType.setType_gamme(gamme.getType_gamme());
		entityManager.merge(gamme);
		entityManager.getTransaction().commit();
		entityManager.clear();

	}

	public void delete(Gamme gamme) {
		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		
		Gamme g = entityManager.find(Gamme.class, gamme.getIdGamme());
		entityManager.remove(g);
		entityManager.getTransaction().commit();
		entityManager.clear();
	}

	public List<Gamme> findAll() {
		entityManager.getTransaction().begin();
		CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Gamme.class));
		return entityManager.createQuery(cq).getResultList();
	}

	public Gamme find(int entityID) {

		return entityManager.find(Gamme.class, entityID);
	}
}
