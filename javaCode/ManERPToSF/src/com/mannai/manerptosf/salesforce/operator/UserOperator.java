package com.mannai.manerptosf.salesforce.operator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.mannai.manerptosf.entity.Salesman;
import com.mannai.manerptosf.salesforce.connection.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.soap.enterprise.sobject.User;

public class UserOperator extends SalesForceOperator {
	
	public static final String EXTERNAL_ID = "Sales_Man_ID__c";
	
	public UserOperator(Connector connector, Logger logger, EntityManager em) {
		super(connector, logger, em);
	}
	
	@Override
	protected SObject[] getSObjects(List<Object> objects) {
		ArrayList<SObject> sObjects = new ArrayList<SObject>();
		for(Object object : objects) {
			Salesman salesman = (Salesman) object;
			User user = new User();
			boolean isActive = salesman.getActive() != null && "true".equalsIgnoreCase(salesman.getActive()); 
			user.setIsActive(isActive);
			user.setFirstName(salesman.getName());
			user.setLastName(salesman.getName());
			String alias = salesman.getName() == null ? 
					"" : salesman.getName().length() > 3 ? 
							salesman.getName().substring(0, 3) : salesman.getName();
			user.setAlias(alias);
			user.setCommunityNickname(alias);
			user.setTimeZoneSidKey("Asia/Riyadh");
			user.setLocaleSidKey("ar");
			user.setEmailEncodingKey("UTF-8");
			user.setProfileId("00eb0000000MToY");
			user.setLanguageLocaleKey("en_US");
			user.setUsername(salesman.getEmail());
			user.setSales_Man_ID__c(salesman.getSalesmanId());
			user.setEmail(salesman.getEmail());
			sObjects.add(user);
		}
		return sObjects.toArray(new SObject[] {});
	}
	
	public void upsert(SObject[] sObjects) {
		EnterpriseConnection connection = connector.getConnection();
		for(int i = 0; i < sObjects.length; i++) {
			User user = (User) sObjects[i];
			try {
				UpsertResult[] results = connection.upsert(EXTERNAL_ID, new SObject[] {user});
				for(UpsertResult result : results) {
					if(!result.isSuccess()) {
						logger.log(Level.FINE, "Couldn't upsert user " + user.getSales_Man_ID__c() + " reson is " + result.toString());
						exceptionLogDao.updateOrCreateExceptionLog(User.class.getSimpleName() + "_" + user.getSales_Man_ID__c(), 
								user.getSales_Man_ID__c(), result.toString());
					} else
						logger.log(Level.FINE, "Successfully inserted User " + user.getSales_Man_ID__c());
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
