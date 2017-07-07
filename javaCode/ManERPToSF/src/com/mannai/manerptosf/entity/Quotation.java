package com.mannai.manerptosf.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
	@NamedQuery(name="Quotation.findAll", query="Select q from Quotation q"),
	@NamedQuery(name="Quotation.findAccordingToDateCreatedAndDateModified", query="Select q from Quotation q where q.createdDate = :date or q.modifiedDate = :date")
})

@Entity
@Table(name="MWSM_ENQ_QUOT_DETIALS_SF")
public class Quotation {
	@Id
	@Column(name="CE_BU_ENQNO")
	private String besuinessUnitEnqNo;
	@Column(name="CE_ENQUIRY_NO")
	private long enquiryNumber;
	@Column(name="CE_BUSINESS_UNIT")
	private String businessUnit;
	@Column(name="CE_BU_NAME")
	private String businessUnitName;
	@Column(name="CE_SECTION_CODE")
	private String sectionCode;
	@Column(name="CE_SECTION_NAME")
	private String sectionName;
	@Column(name="CE_QUOTE_NO")
	private String quoteNumber;
	@Column(name="CE_AMOUNT")
	private double amount;
	@Column(name="CE_CUST_ID")
	private String customerId;
	@Column(name="CE_ACCOUNT_NO")
	private String accountNumber;
	@Column(name="CE_ACCOUNT_NAME")
	private String accountName;
	@Column(name="CE_CLOSE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date closeDate;
	@Column(name="CE_CREATED_BY")
	private String createdBy;
	@Column(name="CE_SALESMAN")
	private String salesMan;
	@Column(name="CE_ENQ_DESC")
	private String enquiryDescription;
	@Column(name="CE_STAGE")
	private String stage;
	@Column(name="CE_BID_SUBMISSION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date bidSubmissionDate;
	@Column(name="CE_ESTIMATED_GM_PERC")
	private double estimatedGMPerc;
	@Column(name="DATE_CREATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name="DATE_MODIFIED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	@Column(name="CE_SALESMAN_ID")
	private String salesmanId;
	@Column(name="CE_ESTIMATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date estimateDate;
	@Column(name="CE_SF_ACCNAME")
	private String accName;

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getBesuinessUnitEnqNo() {
		return besuinessUnitEnqNo;
	}
	
	public String getBusinessUnit() {
		return businessUnit;
	}
	
	public String getBusinessUnitName() {
		return businessUnitName;
	}
	
	public String getSectionCode() {
		return sectionCode;
	}
	
	public String getSectionName() {
		return sectionName;
	}
	
	public long getEnquiryNumber() {
		return enquiryNumber;
	}
	
	public String getQuoteNumber() {
		return quoteNumber;
	}
	
	public double getAmount() {
		return amount;
	}
	
	//public String getCustomerId() {
	//	return customerId;
	//}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public String getAccountName() {
		return accountName;
	}
	
	public Date getCloseDate() {
		return closeDate;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	
	public String getSalesMan() {
		return salesMan;
	}
	
	public String getEnquiryDescription() {
		return enquiryDescription;
	}
	
	public String getStage() {
		return stage;
	}
	
	public Date getBidSubmissionDate() {
		return bidSubmissionDate;
	}
	
	public double getEstimatedGMPerc() {
		return estimatedGMPerc;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public Date getModifiedDate() {
		return modifiedDate;
	}
	
	public String getSalesmanId() {
		return this.salesmanId;
	}
	
	public Date getEstimateDate() {
		return this.estimateDate;
	}
	
}
