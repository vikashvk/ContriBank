package com.cb.test;



import java.util.List;

import org.junit.Assert;
import org.junit.Test;




import com.cb.bean.Transaction;
import com.cb.service.BankingService;
import com.cb.service.BankingServiceImpl;


public class JUnitTesting {
	
	BankingService daoTest = new BankingServiceImpl();
	  
	  @Test
	  public void testSearchForInvalidUserId() { 
	  
	   boolean AccNo  = daoTest.isUserExist("User1000");
	    Assert.assertTrue(AccNo);;
	  }
	  
	  @Test
	  public void testSearchForInvalidAccountId() { 
	  
	   List<Integer> AccNo  = daoTest.getUserAccounts("User1000");
	    Assert.assertNotNull(AccNo);
	  }
	  
	  @Test
	  public void testSearchForInvalidTransactions() { 
	  
		List<Transaction> AccNo  = daoTest.miniStatement(10000020);
	    Assert.assertNotNull(AccNo);;
	  }
	  
	  @Test
	  public void testSearchForInvalidCustomer() { 
	  
	   String add  = daoTest.updateAddress("User1000","myaddress");
	    Assert.assertNotNull(add);
	  }

}
