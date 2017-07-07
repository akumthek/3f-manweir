package com.mannai.manerptosf.salesforce.connection;

import com.sforce.soap.enterprise.EnterpriseConnection;

public interface Connector {
	public EnterpriseConnection getConnection();
	public void logOut();
}
