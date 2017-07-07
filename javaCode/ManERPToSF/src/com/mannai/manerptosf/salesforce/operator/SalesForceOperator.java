package com.mannai.manerptosf.salesforce.operator;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.mannai.manerptosf.dao.ExceptionLogDAO;
import com.mannai.manerptosf.salesforce.connection.Connector;
import com.sforce.soap.enterprise.sobject.SObject;

public abstract class SalesForceOperator {
	Connector connector;
	Logger logger;
	EntityManager em;
	private String externalId = null;
	ExceptionLogDAO exceptionLogDao = null;
	
	
	
	
	public SalesForceOperator(Connector connector, Logger logger, EntityManager em) {
		this.connector = connector;
		this.logger = logger;
		this.em = em;
		this.exceptionLogDao = new ExceptionLogDAO(logger, em);
	}
	
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getExternalId() {
		return this.externalId;
	}
	

	protected abstract SObject[] getSObjects(List<Object> objects);
	
	public abstract void upsert(SObject[] objects);
	
	
	public final void operate(List objects) {
		SObject[] objectsToOperate = getSObjects(objects);
		upsert(objectsToOperate);
	}
}
