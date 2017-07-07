package com.mannai.manerptosf.dao;

import java.util.logging.Logger;

import javax.persistence.EntityManager;

public class DAO {
	Logger logger;
	EntityManager em;
	
	public DAO(Logger logger, EntityManager em) {
		this.logger = logger;
		this.em = em;
	}
}
