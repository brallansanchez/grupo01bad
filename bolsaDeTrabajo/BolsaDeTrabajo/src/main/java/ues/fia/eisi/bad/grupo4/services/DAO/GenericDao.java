package ues.fia.eisi.bad.grupo4.services.DAO;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <T extends Serializable>{
	
	public void setClazz(Class<T> clazzToSet);
	public T findOne(final Long id);
	public List<T> findAll();
	public void create(final T entity);
	public T update(final T entity);
	public void delete(final T entity);
	public void deleteById(final Long entityId);
	public void jpqlQuery(final String jpql);
}
