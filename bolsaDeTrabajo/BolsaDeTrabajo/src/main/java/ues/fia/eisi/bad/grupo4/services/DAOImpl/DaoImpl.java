package ues.fia.eisi.bad.grupo4.services.DAOImpl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ues.fia.eisi.bad.grupo4.services.DAO.AbstractGenericDao;
import ues.fia.eisi.bad.grupo4.services.DAO.GenericDao;



@Repository
public class DaoImpl<T extends Serializable> 
		extends AbstractGenericDao<T> 
		implements GenericDao<T>{
}
