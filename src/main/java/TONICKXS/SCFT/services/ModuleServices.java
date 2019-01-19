package TONICKXS.SCFT.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

import TONICKXS.SCFT.entities.Gamme;
import TONICKXS.SCFT.entities.Machine;
import TONICKXS.SCFT.entities.Module;
import TONICKXS.SCFT.utilities.GenericDAO;

/**
 * Session Bean implementation class ModuleServices
 */

public class ModuleServices {

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

	public ModuleServices() {

	}

	public void add(Module module) {
		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}

		entityManager.persist(module);
		entityManager.getTransaction().commit();
		entityManager.clear();
	}

	public void update(Module module, int id) {
		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}

		Module existingModule = entityManager.find(Module.class, id);
		existingModule.setModule_type(module.getModule_type());
		existingModule.setNom_Module(module.getNom_Module());
		existingModule.setVariantes(module.getVariantes());
		entityManager.getTransaction().commit();

	}

	public void delete(Module module) {
		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}

		Module b1 = entityManager.find(Module.class, module.getIdModule());

		entityManager.remove(b1);
		entityManager.getTransaction().commit();
		entityManager.clear();
	}

	public List<Module> findAll() {

		CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Module.class));
		return entityManager.createQuery(cq).getResultList();

	}

}
