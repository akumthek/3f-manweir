package com.mannai.manerptosf.salesforce.operator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.mannai.manerptosf.entity.Customer;
import com.mannai.manerptosf.salesforce.connection.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.Account;
import com.sforce.soap.enterprise.sobject.SObject;

public class AccountOperator extends SalesForceOperator{
	
	public static final String EXTERNAL_ID = "Mannai_ID__c";
	
	public AccountOperator(Connector connector, Logger logger, EntityManager em) {
		super(connector, logger, em);
	}
	
	protected SObject[] getSObjects(List<Object> objects) {
		ArrayList<SObject> sObjects = new ArrayList<SObject>();
		for(Object object : objects) {
			Customer customer = (Customer) object;
			Account account = new Account();
			account.setName(customer.getAccountName());
			account.setMannai_ID__c(new Integer(customer.getAccountId()).toString());
			//account.setAccount_Name_Ext_ID__c(customer.getAccName());
			sObjects.add(account);
		}
		return sObjects.toArray(new SObject[] {});
	}
	
	public void upsert(SObject[] sObjects) {
		EnterpriseConnection connection = connector.getConnection();
		for(int i = 0; i < sObjects.length; i++) {
			Account account = (Account) sObjects[i];
			
			try {
				UpsertResult[] results = connection.upsert(EXTERNAL_ID, new SObject[] {account});
				for(UpsertResult result : results) {
					if(!result.isSuccess()) {
						exceptionLogDao.updateOrCreateExceptionLog(Account.class.getSimpleName() + "_" + account.getMannai_ID__c(), 
								account.getMannai_ID__c(), result.toString());
						logger.log(Level.FINE, "Couldn't upsert account " + account.getMannai_ID__c() + " reson is " + result.toString());
					} else
						logger.log(Level.FINE, "Successfully inserted Account " + account.getMannai_ID__c());
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
