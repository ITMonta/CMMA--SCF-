package TONICKXS.SCFT.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

import TONICKXS.SCFT.entities.Gamme;
import TONICKXS.SCFT.entities.Type;
import TONICKXS.SCFT.entities.Variante_Gamme;

public class VarianteGammeServices {
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
	public VarianteGammeServices() {

	}

	public void add(Variante_Gamme gamme) {
		EntityTransaction tx2 = entityManager.getTransaction();
		tx2.begin();

		entityManager.persist(gamme);
		tx2.commit();
		entityManager.clear();
	}

	public void update(Variante_Gamme gamme) {
		EntityTransaction tx1 = entityManager.getTransaction();
		tx1.begin();
		Variante_Gamme t = entityManager.find(Variante_Gamme.class, gamme.getIdGammevariante());
		entityManager.merge(gamme);
		tx1.commit();
		entityManager.clear();
	}

	public void delete(Variante_Gamme gamme) {
		if(!entityManager.getTransaction().isActive())
		{
			entityManager.getTransaction().begin();
		}
		Variante_Gamme t = entityManager.find(Variante_Gamme.class, gamme.getIdGammevariante());
		if (!entityManager.contains(gamme)) {
		    gamme = entityManager.merge(gamme);
		}

		entityManager.remove(gamme);
		entityManager.getTransaction().commit();
		entityManager.clear();
	}

	public List<Variante_Gamme> findAll() {
		entityManager.getTransaction().begin();
		CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Variante_Gamme.class));
		return entityManager.createQuery(cq).getResultList();
	}

}
