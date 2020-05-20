package ues.fia.eisi.bad.grupo4.services.DAO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
	
	@Transactional(readOnly=true)
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
	public List jpqlQuery(String jpql) {
		return entityManager.createQuery(jpql).getResultList();
	}
	
	@Transactional
	public List jpqlParamsQuery(String jpql, Map<String,Object> params) {
		TypedQuery query = (TypedQuery) entityManager.createQuery(jpql);
		for (Map.Entry<String,Object> entry : params.entrySet()) {
			query.setParameter(entry.getKey(),entry.getValue());
		}
		return query.getResultList();
	}
	
	@Transactional
	public Object jpqlUniqueResult(String jpql, Long id) {
		TypedQuery query = (TypedQuery) entityManager.createQuery(jpql);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	@Transactional
	public int sqlExecution(String sql) {
		javax.persistence.Query query = entityManager.createNativeQuery(sql);
		return query.executeUpdate();
	}
	
	@Transactional
	public void createAny(Object object) {
		entityManager.persist(object);
	}
}
