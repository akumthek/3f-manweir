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
	@NamedQuery(name="mwJob.findAll", query="Select j from mwJob j"),
	@NamedQuery(name="mwJob.findAccordingToDateCreatedAndDateModified", query="Select j from mwJob j where j.createdDate = :date or j.modifiedDate = :date")
})


@Entity
@Table(name="MWSM_MWJOB_SF")
public class mwJob {
	// need to update Id field
	// need to add more mapped fields
	@Id
	@Column(name="JD_BU_ENQNO")
	private String besuinessUnitEnqNo;
	@Column(name="JD_JOB_NO")
	private String jobNo;
	@Column(name="JD_JOB_DATE")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date jobCreatedDate;
	@Column(name="JD_JOB_STATUS")
	private String jobStatus;
	@Column(name="JD_JOB_VALUE")
	private double contractValue;
	
	@Column(name="JD_REQUIRED_DATE")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date jobRequiredDate;
	@Column(name="JD_DELIVERY_DATE")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date jobDeliveredDateFinal;
	@Column(name="JD_CLOSE_DATE")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date jobClosedDate;
	@Column(name="JD_INVOICE_DATE")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date jobInvoicedDate;
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
	
	public String getBesuinessUnitEnqNo() {
		return besuinessUnitEnqNo;
	}
	public String getJobNo() {
		return jobNo;
	}
	public Date getJobCreatedDate() {
		return jobCreatedDate;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public double getContractValue() {
		return contractValue;
	}
	public Date getJobRequiredDate() {
		return jobRequiredDate;
	}
	public Date getJobDeliveredDateFinal() {
		return jobDeliveredDateFinal;
	}
	public Date getJobClosedDate() {
		return jobClosedDate;
	}
	public Date getJobInvoicedDate() {
		return jobInvoicedDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
}
