package com.mannai.manerptosf.general;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mannai.manerptosf.dao.CustomerDAO;
import com.mannai.manerptosf.dao.QuotationDAO;
import com.mannai.manerptosf.dao.SalesmanDAO;
import com.mannai.manerptosf.dao.mwJobDAO;
import com.mannai.manerptosf.entity.Customer;
import com.mannai.manerptosf.entity.Quotation;
import com.mannai.manerptosf.entity.Salesman;
import com.mannai.manerptosf.entity.mwJob;
import com.mannai.manerptosf.salesforce.connection.BasicConnector;
import com.mannai.manerptosf.salesforce.connection.Connector;
import com.mannai.manerptosf.salesforce.operator.AccountOperator;
import com.mannai.manerptosf.salesforce.operator.OpportunityOperator;
import com.mannai.manerptosf.salesforce.operator.SalesForceOperator;
import com.mannai.manerptosf.salesforce.operator.UserOperator;
import com.mannai.manerptosf.salesforce.operator.mwJobOperator;

public class Main {
	
	
	public static void main(String[] args) throws ParseException {
		System.out.println("execution started!!!!!!"+args.length);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		Main main= new Main();
		try{
			if(args.length>0){
				System.out.println("input"+args[0]);
				System.out.println(sdf.parse(args[0]));
				main.execute(sdf.parse(args[0]));
			}else{
				main.execute(main.ConvertDate(new Date()));
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		
		
		
		
		//if(args.length == 1 && "Test".equalsIgnoreCase(args[0])) {
//			Properties properties = new Properties();
//			SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
//			FileInputStream fis = null;
//			
//			Date dateToFetch = null;
//			try {
//				//fis = new FileInputStream(".\\config.properties");
//				fis = new FileInputStream("/orabackup/manerp_exe/mwr/Salesforce_integration/From_ManERP_To_Salesforce/config.properties");
//				
//				properties.load(fis);
//				String dateToFetchStr = properties.getProperty("DATE_TO_FITCH");
//				dateToFetch = sdf.parse(dateToFetchStr);
//			} catch(Exception ex) {
//				ex.printStackTrace();
//				System.exit(0);
//			}
			
		/*} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			Calendar executorCalendar = Calendar.getInstance();
			executorCalendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 17, 25, 0);
			System.out.println("The execution will start at " + executorCalendar.getTime());
			long initialDelayMilliSeconds = executorCalendar.getTime().getTime() - new Date().getTime();
			if(initialDelayMilliSeconds < 0)
				initialDelayMilliSeconds += (24*60*60*1000);
			long intervalDelay = 24*60*60*1000;
			ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
			ScheduledFuture	 scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(new Main().new MoveToSFJob(), 
					initialDelayMilliSeconds, intervalDelay, TimeUnit.MILLISECONDS);
		}*/
	}
	
	
	/*private class MoveToSFJob implements Runnable {
		public void run() {
			new Main().execute(null);
		}
	}*/
	
	private FileHandler createFileHandler() {

		FileHandler fileHandler = null;

		try {String logDirPath = "./logging/";
		File file = new File(logDirPath);
		if(!file.exists())
			file.mkdir();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
			fileHandler = new FileHandler(logDirPath + sdf.format(new Date()) + ".log");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return fileHandler;
	}
	public Date ConvertDate(Date date){

	    DateFormat df = new SimpleDateFormat("dd-M-yyyy");
	    String s = df.format(date);
	    String result = s;
	    try {
	        date=df.parse(result);
	    } catch (ParseException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    return date;
	  }
	
	private void execute(Date dateToFetch) {
		//if(dateToFetch == null)
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ManERPToSF");
		EntityManager em = emf.createEntityManager();
		Connector connector = null;
		
		try {
			FileHandler fileHandler = createFileHandler();
			Logger logger = Logger.getLogger("ManERP to Salesforce");
			if(fileHandler!=null)
			{
			fileHandler.setLevel(Level.ALL);
			logger.setLevel(Level.ALL);
			if(fileHandler != null)
				logger.addHandler(fileHandler);
			}
			ConsoleHandler consoleHandler = new ConsoleHandler();
			consoleHandler.setLevel(Level.ALL);
			logger.addHandler(consoleHandler);
			
			/*
			SalesmanDAO salesmanDao = new SalesmanDAO(logger, em);
			List<Salesman> salesmen = salesmanDao.findAccordingToDateCreatedAndDateModified(dateToFetch); */
			
			//CustomerDAO customerDao = new CustomerDAO(logger, em);
			//List<Customer> customers = customerDao.findAccordingToDateCreatedAndDateMofified(dateToFetch);
			
			/*
			QuotationDAO quotationDao = new QuotationDAO(logger, em);
			List<Quotation> quotations = quotationDao.findAccordingToDateCreatedAndDateMofified(dateToFetch); */
			
			mwJobDAO jobDao = new mwJobDAO(logger, em);
			//List<mwJob> jobs = jobDao.findAccordingToDateCreatedAndDateModified(dateToFetch);
			// change the finadAll() fun
			List<mwJob> jobs = jobDao.findAll();
			connector = new BasicConnector(logger);
			
			SalesForceOperator operator = new mwJobOperator(connector, logger, em);
			operator.operate(jobs); 

			/*
			SalesForceOperator operator = new UserOperator(connector, logger, em);
			operator.operate(salesmen); */
			
			//operator = new AccountOperator(connector, logger, em);
			//operator.operate(customers);
			
			/*
			operator = new OpportunityOperator(connector, logger, em);
			operator.operate(quotations); */
			
		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			em.close();
			emf.close();
			connector.logOut();
		}
		
	}
}