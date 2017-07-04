trigger mwUpdateOppIdTrigger on mwJob__c (before insert)
{
    // get JobNo from Job e.g. '10001-02'
    // get substring before char '-' e.g. '10001'
    // search Opportunity with Quotation No = substring
    // associate this Opportunity to Job
    
    if(Trigger.new[0].mwJobNo__c!=null)
    {
        String JobNo = Trigger.new[0].mwJobNo__c;
        String subJobNo = JobNo.substringBefore('-');
        
        if(subJobNo.length()>0)
        {
            List<opportunity> opp = [select id,name from opportunity where Quotation_No__c like :subJobNo limit 1];
            
            if(opp.size()>0)
            {
                Trigger.new[0].mwOpportunity__c = opp[0].id;
            }
        }
    }

}