package com.mannai.manerptosf.salesforce.connection;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.ws.ConnectorConfig;

public class BasicConnector implements Connector {
	//public static final String USER_NAME = "islam.dawood@mannai.com.qa.dev";
	//public static final String PASSWORD = "SalesForceP@ssw0rd";
	//public static final String AUTH_Endpoint = "https://test.salesforce.com/services/Soap/c/35.0/0DF2500000000Jc";
	public static final String USER_NAME = "integration@mannai.com.qa";
	public static final String PASSWORD = "salesforce1";
	public static final String AUTH_Endpoint = "https://login.salesforce.com/services/Soap/c/35.0/0DF2500000000Jc";

	private static EnterpriseConnection connection = null;
	private Logger logger = null;
	
	public BasicConnector(Logger logger) {
		this.logger = logger;
	}
	
	@Override
	public EnterpriseConnection getConnection() {
		ConnectorConfig config = new ConnectorConfig();
		config.setUsername(USER_NAME);
		config.setPassword(PASSWORD);
		config.setAuthEndpoint(AUTH_Endpoint);
		try {
			if(connection == null)
				System.out.println("Connecing to.."+AUTH_Endpoint);
				connection = new EnterpriseConnection(config);
			logger.log(Level.INFO, "Connected successfully to Salesforce");
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.log(Level.INFO, "Failed to connect to Salesforce - " + ex.getMessage());
		}
		return connection;
	}
	@Override
	public void logOut() {
		try {
			connection.logout();
		} catch(Exception ex) {
			System.out.println("Couldn't logout, Exception happened. " + ex.getMessage());
		}
		
	}
	
	
	
	
}
