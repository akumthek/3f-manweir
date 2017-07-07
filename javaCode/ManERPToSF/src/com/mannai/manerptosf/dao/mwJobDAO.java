package com.mannai.manerptosf.dao;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import com.mannai.manerptosf.entity.mwJob;

public class mwJobDAO extends DAO{
	
	public mwJobDAO(Logger logger, EntityManager em) {
		super(logger, em);
	}

	public List<mwJob> findAll() {
		TypedQuery<mwJob> query = em.createNamedQuery("mwJob.findAll", mwJob.class);
		List<mwJob> jobs = query.getResultList();
		logger.log(Level.INFO, "Fetched all jobs from the database");
		return jobs;
	}
	
	public List<mwJob> findAccordingToDateCreatedAndDateModified(Date date) {
		TypedQuery<mwJob> query = em.createNamedQuery("mwJob.findAccordingToDateCreatedAndDateModified", mwJob.class);
		query.setParameter("date", date, TemporalType.DATE);
		List<mwJob> jobs = query.getResultList();
		logger.log(Level.INFO, "fetched jobs with creation or update date = " + date);
		return jobs;
	}
	
}
