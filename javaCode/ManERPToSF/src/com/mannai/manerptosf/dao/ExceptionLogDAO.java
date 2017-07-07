package com.mannai.manerptosf.dao;

import java.util.Date;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.mannai.manerptosf.entity.ExceptionLog;

public class ExceptionLogDAO extends DAO {
	
	private Date errorDate = null;
	public ExceptionLogDAO(Logger logger, EntityManager em) {
		super(logger, em);
		this.errorDate = new Date();
	}
	
	public void updateOrCreateExceptionLog(String entityId, String id, String errorMessage) {
		ExceptionLog exceptionLog = em.find(ExceptionLog.class, entityId);
		if(exceptionLog == null) {
			exceptionLog = new ExceptionLog();
		}
		exceptionLog.setEntityId(entityId);
		exceptionLog.setId(id);
		exceptionLog.setErrorMessage(errorMessage);
		exceptionLog.setErrorDate(errorDate);
		em.getTransaction().begin();
		em.persist(exceptionLog);
		em.getTransaction().commit();
	}
}
