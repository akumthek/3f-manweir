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
		@NamedQuery(name="Salesman.findAll", query="Select s from Salesman s"),
		@NamedQuery(name="Salesman.findAccordingToDateCreatedAndDateModified", query="Select s from Salesman s where s.createdDate = :date or s.modifiedDate = :date")
})


@Entity
@Table(name="MWSM_SALESMAN_SF")
public class Salesman {
	@Id
	@Column(name="SALESMAN_ID")
	private String salesmanId;
	@Column(name="NAME")
	private String name;
	@Column(name="NICK_NAME")
	private String knickName;
	@Column(name="ALIAS")
	private String alias;
	@Column(name="PROFILE")
	private String profile;
	@Column(name="ACTIVE")
	private String active;
	@Column(name="EMAIL_ID")
	private String email;
	@Column(name="USER_NAME")
	private String userName;
	@Column(name="BUSINESS_UNIT")
	private String businessUnit;
	@Column(name="DIVISION")
	private String division;
	@Column(name="CREATED_DATE")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name="MODIFIED_DATE")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date modifiedDate;
	
	public String getSalesmanId() {
		return salesmanId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getKnickName() {
		return knickName;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public String getProfile() {
		return profile;
	}
	
	public String getActive() {
		return active;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getBusinessUnit() {
		return businessUnit;
	}
	
	public String getDivision() {
		return division;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}
}
