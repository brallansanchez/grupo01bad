package ues.fia.eisi.bad.grupo4.services.DAO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDao <T extends Serializable>{
	
	//Metodo para colocar la clase al genericDao.
	public void setClazz(Class<T> clazzToSet);
	
	//Metodo para encontrar un objeto de la clase configurada.
	public T findOne(final Long id);
	
	//Metodo para listar todos los registros de la clase configurada.
	public List<T> findAll();
	
	//Metodo para crear un objeto de la clase configurada.
	public void create(final T entity);
	
	//Metodo para actualizar un objeto de la clase configurada.
	public void update(final T entity);
	
	//Metodo para eliminar un objeto de la clase configurada.
	public void delete(final T entity);
	
	//Metodo para eliminar por id un objeto de la clase configurada.
	public void deleteById(final Long entityId);
	
	//Metodo para listar los registros de una entidad mediante
	// una jpql query enviada como parametro.
	public List jpqlQuery(final String jpql);
	
	//Metodo para listar los registros de una entidad mediante
	//una jpql query enviada y mediante un mapa de parametros.
	public List jpqlParamsQuery(final String jpql, Map<String,Object> params);
	
	//Metodo para obtener un registro de una entidad mediante
	//una jpql query y un id enviados como parametros.
	public Object jpqlUniqueResult(final String jqpl, Long id);
	
	//Metodo para realizar la ejecucion de una query sql nativa.
	public int sqlExecution(final String sql);
	
	//Metodo para crear un registro de cualquier entidad.
	public void createAny(final Object object);
	
	//Metodo para actualizar un registro de cualquier entidad.
	public void updateAny(final Object object);
	
	//Metodo para leer un objeto de cualquier entidad mediante
	//una sql y un mapa.
	public Object getUniqueValue(final String sql, final Map<String,Object> params);
	
	//Metodo para leer un registro de una tabla mediante una jpql y mapa de valores.
	public Object jpqlParamsUniqueQuery(final String jpql, final Map<String,Object> params);
}
