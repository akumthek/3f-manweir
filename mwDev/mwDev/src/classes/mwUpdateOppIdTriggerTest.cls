@IsTest global with sharing class mwUpdateOppIdTriggerTest
{
    static Opportunity opp;
   
    private static void init()
    {
        Account acc = new Account(name = 'testAcc01');
        insert acc;
        
        opp = new Opportunity(name = 'opp001' , Quotation_No__c = '1000', StageName = 'Prospecting' , CloseDate = system.TODAY(), 
        ForecastCategoryName = 'Pipeline', Account = acc);        
            
        insert opp;
    }
    
    public static testMethod void testOppId()
    {
        init();

        mwJob__c job = new mwJob__c(name = 'job001' , mwJobNo__c = '1000-4');
        insert job;   
        
        mwJob__c job_new = [select id, mwOpportunity__c from mwJob__c where name = :job.name limit 1];
        System.assertEquals(job_new.mwOpportunity__c,opp.id);
        
    }
    
    public static testMethod void testNoOppId()
    {
        init();

        mwJob__c job = new mwJob__c(name = 'job002' , mwJobNo__c = '10002-4');
        insert job;   
        
        mwJob__c job_new = [select id, mwOpportunity__c from mwJob__c where name = :job.name limit 1];
        System.assertEquals(job_new.mwOpportunity__c,null);
        
    }
        
}