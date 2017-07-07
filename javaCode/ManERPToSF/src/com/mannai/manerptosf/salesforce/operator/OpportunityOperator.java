package com.mannai.manerptosf.salesforce.operator;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.mannai.manerptosf.entity.Quotation;
import com.mannai.manerptosf.salesforce.connection.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.Account;
import com.sforce.soap.enterprise.sobject.Opportunity;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.soap.enterprise.sobject.User;

public class OpportunityOperator extends SalesForceOperator {
	public static final String EXTERNAL_ID = "Unique_Key__c";
	
	public OpportunityOperator(Connector connector, Logger logger, EntityManager em) {
		super(connector, logger, em);
	}

	@Override
	protected SObject[] getSObjects(List<Object> objects) {
		ArrayList<SObject> sObjects = new ArrayList<SObject>();
		for(Object object : objects) {
			Quotation quotation = (Quotation) object;
			Opportunity opportunity = new Opportunity();
			opportunity.setUnique_Key__c(quotation.getBesuinessUnitEnqNo());
			Account account = new Account();
			//account.setMannai_ID__c(quotation.getAccountNumber());
			account.setAccount_Name_Ext_ID__c(quotation.getAccName());

			opportunity.setAccount(account);
			opportunity.setAccount_Mannai_Id__c(account.getMannai_ID__c());
			opportunity.setAmount(quotation.getAmount());
			if(quotation.getCloseDate() != null) {
				Calendar closeDate = Calendar.getInstance();
				closeDate.setTime(quotation.getCloseDate());
				opportunity.setCloseDate(closeDate);
			}
			User user = new User();
			user.setSales_Man_ID__c(quotation.getSalesmanId());
			opportunity.setOwner(user);
			opportunity.setSales_Man_ID__c(quotation.getSalesmanId());
			
			opportunity.setDescription(quotation.getEnquiryDescription());
			String quotationName = quotation.getEnquiryDescription();
			if(quotationName == null)
				quotationName = "";
			if(quotationName.length() > 120)
				quotationName = quotationName.substring(0, 120);
			opportunity.setName(quotationName);
			opportunity.setStageName(quotation.getStage());
			if(quotation.getBidSubmissionDate() != null) {
				Calendar bidSubmissionDate = Calendar.getInstance();
				bidSubmissionDate.setTime(quotation.getBidSubmissionDate());
				bidSubmissionDate.set(Calendar.HOUR_OF_DAY, 3);
				opportunity.setBid_Submission_Date__c(bidSubmissionDate);
			}
			opportunity.setBusiness_Unit__c(quotation.getBusinessUnitName());
			opportunity.setEstimated_GM__c(quotation.getEstimatedGMPerc() == 0 ? 0.1 : quotation.getEstimatedGMPerc());
			opportunity.setEnquiry_No__c(new Long(quotation.getEnquiryNumber()).toString());
			opportunity.setQuotation_No__c(quotation.getQuoteNumber());
			opportunity.setSection_Name__c(quotation.getSectionName());
			opportunity.setCash_Customer_Name__c(quotation.getAccountName());
			if(quotation.getCreatedDate() != null) {
				Calendar createdDate = Calendar.getInstance();
				createdDate.setTime(quotation.getCreatedDate());
//				opportunity.setCreatedDate(createdDate);
			}
			//modified by fyaz
			if(quotation.getEstimateDate() != null) {
				Calendar dateModified = Calendar.getInstance();
				dateModified.setTime(quotation.getEstimateDate());
//				Here also we shouldn't insert dateModfified.
				opportunity.setQuote_Created_Date__c(dateModified);
			}
			sObjects.add(opportunity);
		}
		return sObjects.toArray(new SObject[] {});
	}

	public void upsert(SObject[] sObjects) {
		EnterpriseConnection connection = connector.getConnection();
		for(int i = 0; i < sObjects.length; i++) {
			Opportunity opportunity = (Opportunity) sObjects[i];
			try {
				UpsertResult[] results = connection.upsert(EXTERNAL_ID, new SObject[] {opportunity});
				for(UpsertResult result : results) {
					if(!result.isSuccess()) {
						logger.log(Level.FINE, "Couldn't upsert opportunity " + opportunity.getUnique_Key__c() + " reson is " + result.toString());
						exceptionLogDao.updateOrCreateExceptionLog(Opportunity.class.getSimpleName() + "_" + opportunity.getUnique_Key__c(), 
								opportunity.getUnique_Key__c(), result.toString());
					} else {
						System.out.println(opportunity.getUnique_Key__c() + " >>>> " + opportunity.getBid_Submission_Date__c().getTime() + " >>> " + opportunity.getBid_Submission_Date__c().getTimeZone());
						logger.log(Level.FINE, "Successfully inserted opportunity " + opportunity.getUnique_Key__c());
					}
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
