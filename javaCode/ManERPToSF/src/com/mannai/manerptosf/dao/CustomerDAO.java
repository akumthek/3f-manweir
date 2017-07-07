package com.mannai.manerptosf.dao;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import com.mannai.manerptosf.entity.Customer;

public class CustomerDAO extends DAO{
	
	public CustomerDAO(Logger logger, EntityManager em) {
		super(logger, em);
	}

	public List<Customer> findAll() {
		TypedQuery<Customer> query = em.createNamedQuery("Customer.findAll", Customer.class);
		List<Customer> customers = query.getResultList();
		logger.log(Level.INFO, "Fetched all customers from the database");
		return customers;
	}
	
	public List<Customer> findAccordingToDateCreatedAndDateMofified(Date date) {
		TypedQuery<Customer> query = em.createNamedQuery("Customer.findAccordingToDateCreatedAndDateModified", Customer.class);
		query.setParameter("date", date, TemporalType.DATE);
		List<Customer> customers = query.getResultList();
		logger.log(Level.INFO, "fetched customers with creation or update date = " + date);
		return customers;
	}
	
}
