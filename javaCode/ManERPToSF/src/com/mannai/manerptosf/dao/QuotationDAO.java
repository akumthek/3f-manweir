package com.mannai.manerptosf.dao;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import com.mannai.manerptosf.entity.Quotation;


public class QuotationDAO extends DAO{
	
	public QuotationDAO(Logger logger, EntityManager em) {
		super(logger, em);
	}
	
	public List<Quotation> findAll() {
		logger.log(Level.INFO, "Created entity manager");
		TypedQuery<Quotation> query = em.createNamedQuery("Quotation.findAll", Quotation.class);
		List<Quotation> quotations = query.getResultList();
		logger.log(Level.INFO, "Fetched all quotations from the database");
		return quotations;
	}
	
	public List<Quotation> findAccordingToDateCreatedAndDateMofified(Date date) {
		logger.log(Level.INFO, "Created entity manager");
		TypedQuery<Quotation> query = em.createNamedQuery("Quotation.findAccordingToDateCreatedAndDateModified", Quotation.class);
		query.setParameter("date", date, TemporalType.DATE);
		List<Quotation> quotations = query.getResultList();
		logger.log(Level.INFO, "fetched quotations with creation or update date = " + date);
		return quotations;
	}
}