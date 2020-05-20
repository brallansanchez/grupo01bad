package ues.fia.eisi.bad.grupo4.services.DAO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDao <T extends Serializable>{
	
	public void setClazz(Class<T> clazzToSet);
	public T findOne(final Long id);
	public List<T> findAll();
	public void create(final T entity);
	public void update(final T entity);
	public void delete(final T entity);
	public void deleteById(final Long entityId);
	public List jpqlQuery(final String jpql);
	public List jpqlParamsQuery(final String jpql, Map<String,Object> params);
	public Object jpqlUniqueResult(final String jqpl, Long id);
	public int sqlExecution(final String sql);
	public void createAny(final Object object);
}
