package ues.fia.eisi.bad.grupo4.services.DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractGenericDao<T extends Serializable> {
	private Class<T> clazz;
	
	@PersistenceContext
	EntityManager entityManager;
	
	public void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}
	
	@Transactional
	public T findOne(Long id) {
		return entityManager.find(clazz, id);
	}
	
	@Transactional
	public List<T> findAll(){
		return entityManager.createQuery("from " + clazz.getName()).getResultList();
	}
	
	@Transactional
	public void create(T entity) {
		entityManager.persist(entity);
	}
	
	@Transactional
	public void update(T entity) {
		entityManager.merge(entity);
		entityManager.flush();
	}
	
	@Transactional
	public void delete(T entity) {
		entityManager.remove(entity);
	}
	
	@Transactional
	public void deleteById(Long entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}
	
	@Transactional
	public void jpqlQuery(String jpql) {
		
	}
}
