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
	@NamedQuery(name="Customer.findAll", query="Select c from Customer c"),
	@NamedQuery(name="Customer.findAccordingToDateCreatedAndDateModified", query="Select c from Customer c where c.createdDate = :date or c.modifiedDate = :date")
})


@Entity
@Table(name="MWSM_CUSTOMER_ACCOUNTS_SF")
public class Customer {
	@Id
	@Column(name="ACCOUNT_ID")
	private int accountId;
	@Column(name="ACCOUNT_NAME")
	private String accountName;
	@Column(name="ACCOUNT_SITE")
	private String accountSite;
	@Column(name="ACCOUNT_NO")
	private String accountNumber;
	@Column(name="BILLING_ADDRESS")
	private String billingAddress;
	@Column(name="PHONE")
	private String phone;
	@Column(name="BU_ID")
	private String buId;
	@Column(name="CREATED_DATE")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name="MODIFIED_DATE")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date modifiedDate;
	//@Column(name="CE_SF_ACCNAME")
	//private String accName;

//	public String getAccName() {
//		return accName;
//	}
//	public void setAccName(String accName) {
//		this.accName = accName;
//	}
	public int getAccountId() {
		return accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public String getAccountSite() {
		return accountSite;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public String getPhone() {
		return phone;
	}
	public String getBuId() {
		return buId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
}
