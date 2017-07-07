package com.mannai.manerptosf.dao;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import com.mannai.manerptosf.entity.Salesman;

public class SalesmanDAO extends DAO{

	public SalesmanDAO(Logger logger, EntityManager em) {
		super(logger, em);
	}
	
	public List<Salesman> findAll() {
		logger.log(Level.INFO, "Created entity manager");
		TypedQuery<Salesman> query = em.createNamedQuery("Salesman.findAll", Salesman.class);
		List<Salesman> result = query.getResultList();
		logger.log(Level.INFO, "Fetched all salesmen from the database");
		System.out.println("The found list size = " + result.size());
		return result;
	}

	
	public List<Salesman> findAccordingToDateCreatedAndDateModified(Date date) {
		logger.log(Level.INFO, "Created entity manager");
		TypedQuery<Salesman> query = em.createNamedQuery("Salesman.findAccordingToDateCreatedAndDateModified", Salesman.class);
		query.setParameter("date", date, TemporalType.DATE);
		List<Salesman> result = query.getResultList();
		logger.log(Level.INFO, "fetched salesmen with creation or update date = " + date);
		return result;
	}
}
