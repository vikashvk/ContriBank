package com.cb.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;













import com.cb.bean.Payee;
import com.cb.bean.ServiceTracker;
import com.cb.bean.Transaction;
import com.cb.exception.BankingException;
import com.cb.service.AdminService;
import com.cb.service.AdminServiceImpl;
import com.cb.service.BankingService;
import com.cb.service.BankingServiceImpl;
import com.cb.service.LoginService;
import com.cb.service.LoginServiceImpl;



public class Home 
{
	static BankingService bSer = new BankingServiceImpl();
	static Scanner sc = new Scanner(System.in);
	Logger homeLogger = null;
	
	public void UserHome(String userName)
//	public void Home(int userName)
	{
		int acChoice;		//to store ac no on which operations to be operated
		List<Transaction> tnx;
		homeLogger = Logger.getLogger(Home.class);
		PropertyConfigurator.configure("log4j.properties");
				
		while(true)
		{
			//operation list
			System.out.println("\n\n>> Choose your Operation:");
			System.out.println("   Press 1 to View Transaction Statement");
			System.out.println("   Press 2 to Change Address");
			System.out.println("   Press 3 to Requisition for Cheque Book");
			System.out.println("   Press 4 to Track Service Request");
			System.out.println("   Press 5 to Fund Transfer");
			System.out.println("   Press 6 to Change Password");
			System.out.println("   Press 9 to Log Out");
			 
			//switch for operations
			switch(sc.nextInt())
			{
				case 1: 
					System.out.println("1>> Mini Statement\n2>> Detailed Statement");
					switch(sc.nextInt())
					{
						case 1:	
							acChoice=bSer.getCurrentAcNo(userName);
							tnx =  bSer.miniStatement(acChoice);
							if(!tnx.isEmpty())
							{
								System.out.println("Tnx ID\tTnx Date\tType\tAmount\tDescription");
								for(Transaction t:tnx)
									System.out.println(t.getTransaction_ID()+"\t"+t.getDateOfTransaction()
														+"\t"+t.getTransactionType()+"\t"+t.getTranAmount()+"\t"+t.getTran_description());
							}
							else
								System.out.println("No Transaction made by you till now.");
							break;
							
						case 2:
							AdminService aser = new AdminServiceImpl();
							acChoice=bSer.getCurrentAcNo(userName);
							String startDate = null, endDate = null;
							boolean checkst = false, checked = false;
						 
							do{
									try
									{	
										System.out.println(" >> Enter Start Date in DD-Mon-YY format:");
										startDate=sc.next();
										startDate = startDate.toUpperCase();
										System.out.println(startDate);
										checkst = aser.validateDate(startDate);
									}
									catch(BankingException e)
									{
										e.getMessage();
									}
								}while(!checkst);
										
							
							do{
								try{
										System.out.println(" >> Enter End Date in DD-Mon-YY format:");
										endDate=sc.next();
										endDate = endDate.toUpperCase();
										checked = aser.validateDate(endDate);
									}
									catch(BankingException e)
									{
										e.getMessage();
									}
							}while(!checked);
							
							tnx =  bSer.detailedStatement(acChoice,startDate,endDate);
							if(!tnx.isEmpty())
							{
								System.out.println("Tnx ID\tTnx Date\tType\tAmount\tDescription");
								for(Transaction t:tnx)
									System.out.println(t.getTransaction_ID()+"\t"+t.getDateOfTransaction()
														+"\t"+t.getTransactionType()+"\t"+t.getTranAmount()+"\t"+t.getTran_description());
							}
							else
								System.out.println("No Transaction made by you till now.");
							break;
							
						default:
							System.out.println("!! Alert: Invalid Option! Try Again.");
							break;
					}
					break;
					
				case 2:
					System.out.println(" >> Your Current Address:");
					System.out.println(bSer.getCurrentAddress(userName));
					System.out.println(" >> Enter your Current Address:");
					System.out.println(bSer.updateAddress(userName));
					break;
					
				case 3:
					acChoice=bSer.getCurrentAcNo(userName);
					System.out.println(bSer.chequeRequest(acChoice));
					break;
					
				case 4:
					System.out.println("? Do you have Service Request ID: press 1 for 'YES' and 2 for 'NO'");
					switch(sc.nextInt())
					{
						case 1:
							System.out.println(" >> Enter Service Request ID:");
							int requestID=sc.nextInt();
							List<ServiceTracker> serTracker = bSer.getServiceRequestById(userName, requestID);
							if(serTracker.isEmpty())
							{
								System.out.println("!! Alert: Entered wrong Service Request ID.");
							}
							else
							{
								System.out.println("Service ID\tAccount ID\tStatus\tDescription");
								for(ServiceTracker s:serTracker)
								{
									System.out.println(s.getService_ID()+"\t\t"+s.getAccount_ID()+"\t\t"+s.getService_status()+"\t"+s.getService_Description());
								}
								homeLogger.info(":: User requested for services request for Service ID "+requestID);
							}
							break;
						case 2:
							System.out.println("? You would like to view the services requested by you for A/c No.");
							acChoice=bSer.getCurrentAcNo(userName);
							List<ServiceTracker> serviceRqst=bSer.getAllServiceRequested(acChoice);
							if(!serviceRqst.isEmpty())
							{
								System.out.println("Service ID\tAccount ID\tStatus\tDescription");
								for(ServiceTracker s:serviceRqst)
								{
									System.out.println(s.getService_ID()+"\t\t"+s.getAccount_ID()+"\t\t"+s.getService_status()+"\t"+s.getService_Description());
								}

								homeLogger.info(":: User requested for services request by him/her for A/c no. "+acChoice);
							}
							else
								System.out.println(":: You have made no request till now.");
							break;
						default:
							System.out.println("!! Alert: Invalid option! Try again...");
					}
					break;
					
					
				case 5:
					int fromAcChoice,toAcChoice,amt;
					String tnxPassword;
					System.out.println("? You would like to transfer fund to your own account or to others:\n press 1:transfer fund to self\n press 2:transfer fund to others");
					switch (sc.nextInt()) 
					{
						case 1:
							
							System.out.println(" >> Enter your beneficiary account");
							toAcChoice=bSer.getCurrentAcNo(userName);
							do
							{
								System.out.println("? You would like to transfer ammount from");
								fromAcChoice=bSer.getCurrentAcNo(userName);
								if(toAcChoice==fromAcChoice)
									System.out.println("!! Alert: You have selected same Account no.");
							}while(toAcChoice==fromAcChoice);
							System.out.println(" >> Enter fund amount you want to transfer");
							amt=sc.nextInt();
							System.out.println(" >> Enter the transaction password");
							tnxPassword=sc.next();
							while(!bSer.checkTransactionPassword(userName,tnxPassword))
							{
									System.out.println("!! Alert: Wrong transaction password. Enter valid transaction password");
									tnxPassword=sc.next();
							}
							bSer.checkBalanceAndMakeTransaction(toAcChoice,fromAcChoice,amt);
							break;
						case 2:
							System.out.println("Press 1 to Add a new Payee\nPress 2 to Make a Transfer using Registered Payee a/c");
							switch(sc.nextInt())
							{
								case 1:
									System.out.println(bSer.validateAndCreatePayeeAccount(userName));
									break;
								case 2:
									List<Payee> payeeList = bSer.getPayeeAccountId(userName);
									if(!payeeList.isEmpty())
									{
										List<Integer> payeeAcs = new ArrayList<Integer>();
										System.out.println("Payee A/c No. \tNick Name");
										for(Payee p:payeeList)
										{
											System.out.println(p.getPayee_Account_Id()+"\t\t"+p.getNick_name());			
											payeeAcs.add(p.getPayee_Account_Id());
										}
										do
										{
											System.out.println(" >> Enter a Registered Payee Account:");
											toAcChoice=sc.nextInt();
										}while(!payeeAcs.contains(toAcChoice));
										System.out.println("? You would like to transfer ammount from");
										fromAcChoice=bSer.getCurrentAcNo(userName);
										System.out.println(" >> Enter fund amount you want to transfer");
										amt=sc.nextInt();
										System.out.println(" >>Enter the transaction password");
										tnxPassword=sc.next();
										while(!bSer.checkTransactionPassword(userName,tnxPassword))
										{
												System.out.println("!! Alert: Wrong transaction password. Enter valid transaction password");
												tnxPassword=sc.next();
										}
										bSer.checkBalanceAndMakeTransaction(toAcChoice,fromAcChoice,amt);
									}
									else
										System.out.println("!!Alert: No Payee details found. Create One.");
									break;
									
								default:
									break;	
							}
							break;
					}
					break;
						
				case 6:
					System.out.println(" >> Enter your old password");
					String oldPass=sc.next();
					String newPass, newPassC;
					boolean passchk=false;
					LoginService lser =  new LoginServiceImpl();
					do
					{
						
						do
						{
							System.out.println(" >> Enter new Login password: ");
							newPass = sc.next();
							try 
							{
								passchk = lser.validatePassword(newPass);
							} 
							catch (BankingException e) 
							{
								e.getMessage();
							}
						}while(!passchk);
						
						System.out.println(" >> Confirm new password");
						newPassC=sc.next();
						if(!newPass.equals(newPassC))
							System.out.println("!! Alert: New Password and Confirm Password did not matched");
					}while(!newPass.equals(newPassC));
					System.out.println(bSer.changePassword(userName,oldPass,newPass));
					break;
					
				case 9:
					System.out.println("Thank You. \nYou have successfully logged out.");
					Login.login();
					
				default:
					System.out.println("!!Alert: Invalid Option! Try Again...");
			}
		}
	}
}