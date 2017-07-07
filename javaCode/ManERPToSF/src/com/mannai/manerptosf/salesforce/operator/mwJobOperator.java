package com.mannai.manerptosf.salesforce.operator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.mannai.manerptosf.entity.mwJob;
import com.mannai.manerptosf.salesforce.connection.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.MwJob__c;
import com.sforce.soap.enterprise.sobject.SObject;

public class mwJobOperator extends SalesForceOperator{
	
	public static final String EXTERNAL_ID = "mwJobNo__c";
	
	public mwJobOperator(Connector connector, Logger logger, EntityManager em) {
		super(connector, logger, em);
	}
	
	protected SObject[] getSObjects(List<Object> objects) {
		ArrayList<SObject> sObjects = new ArrayList<SObject>();
		for(Object object : objects) {
			mwJob job = (mwJob) object;
			MwJob__c sfJob = new MwJob__c();
			sfJob.setMwJobNo__c(job.getJobNo());
			sfJob.setMwJobStatus__c(job.getJobStatus());
			sfJob.setMwContractValue__c(job.getContractValue());
		// get status and delivery date from ERP, if delivery date is not null and status is Relesed
			// then set partial delivery date = delivery date
			//account.setAccount_Name_Ext_ID__c(customer.getAccName());
			sObjects.add(sfJob);
		}
		return sObjects.toArray(new SObject[] {});
	}
	
	public void upsert(SObject[] sObjects) {
		EnterpriseConnection connection = connector.getConnection();
		for(int i = 0; i < sObjects.length; i++) {
			MwJob__c job = (MwJob__c) sObjects[i];
			
			try {
				UpsertResult[] results = connection.upsert(EXTERNAL_ID, new SObject[] {job});
				for(UpsertResult result : results) {
					if(!result.isSuccess()) {
						exceptionLogDao.updateOrCreateExceptionLog(MwJob__c.class.getSimpleName() + "_" + job.getMwJobNo__c(), 
								job.getMwJobNo__c(), result.toString());
						logger.log(Level.FINE, "Couldn't upsert job " + job.getMwJobNo__c() + " reson is " + result.toString());
					} else
						logger.log(Level.FINE, "Successfully inserted job " + job.getMwJobNo__c());
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
