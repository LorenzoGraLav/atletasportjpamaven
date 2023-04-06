package it.prova.atletasportjpamaven.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


import it.prova.atletasportjpamaven.model.Atleta;
import it.prova.atletasportjpamaven.model.Sport;

public class SportDAOImpl implements SportDAO {
	
	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;

	}


	@Override
	public List<Sport> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sport get(Long id) throws Exception {
		return entityManager.find(Sport.class, id);

	}

	@Override
	public void update(Sport sportInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Sport sportInstance) throws Exception {
		if (sportInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(sportInstance);
	}

	@Override
	public void delete(Sport sportInstance) throws Exception {
		entityManager.remove(entityManager.merge(sportInstance));

	}


	@Override
	public Sport findByDescrizione(String descrizione) throws Exception {
		TypedQuery<Sport> query = entityManager
				.createQuery("select s from Sport s where s.descrizione=?1", Sport.class)
				.setParameter(1, descrizione);
		return query.getResultStream().findFirst().orElse(null);

	}


	@Override
	public List<Atleta> findAllBySport(Sport sportInput) throws Exception {
		TypedQuery<Atleta> query = entityManager.createQuery("select a FROM Atleta a join a.sports s where s = :sport", Atleta.class);
		query.setParameter("sport", sportInput);
		return query.getResultList();
	}

	

}
