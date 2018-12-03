package com.cb.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cb.bean.PayeeTable;
import com.cb.bean.ServiceTracker;
import com.cb.bean.Transaction;
import com.cb.exception.BankingException;
import com.cb.service.BankingService;
import com.cb.service.BankingServiceImpl;
import com.cb.service.LoginService;
import com.cb.service.LoginServiceImpl;

/**
 * @author 
 *
 */
public class Home 
{
	static BankingService bSer = new BankingServiceImpl();
	static Scanner sc = new Scanner(System.in);
	Logger homeLogger = null;
//	
	public void UserHome(String userName)
	{
		int acChoice;		//to store account no on which operations to be operated
		List<Transaction> tnx;//to list the Transactions
		homeLogger = Logger.getLogger(Home.class);//Logger
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
			 int choice=0;
			 
			 do {
		            try {
		            	choice = sc.nextInt();
		            } catch (InputMismatchException e) {
		                System.out.println("Wrong choice.\n Enter again.");
		            }
		            sc.nextLine(); // clears the buffer
		        } while (choice <= 0);
				
			 
			//switch for operations
			switch(choice)
			{
				case 1: 
					System.out.println("1>> Mini Statement\n2>> Detailed Statement");
				    int ch=0;
					do {
			            try {
			            	ch = sc.nextInt();
			            } catch (InputMismatchException e) {
			                System.out.println("Wrong choice.\n Enter again.");
			            }
			            sc.nextLine(); // clears the buffer
			        } while (ch <= 0);
					
					switch(ch)//nested switch
					{
						case 1:	//to display mini statements
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
							
						case 2://to display detailed statements
							acChoice=bSer.getCurrentAcNo(userName);
						 
								
							tnx =  bSer.detailedStatement(acChoice);
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
							System.out.println("!! Alert: Invalid Option! Try Again !!");
							break;
					}
					break;
					
				case 2://to update address
					System.out.println(" >> Enter your new Address:");
					String address=sc.next();
					System.out.println(bSer.updateAddress(userName,address));
					break;
					
				case 3://Request for Cheque
					acChoice=bSer.getCurrentAcNo(userName);
					System.out.println(bSer.chequeRequest(acChoice));
					break;
					
				case 4://Yes/No from user for Service Request ID
					System.out.println(" Do you have Service Request ID ?\n press 1 for 'YES' and 2 for 'NO'");
					
					int uCh=0;
					do {
			            try {
			            	uCh = sc.nextInt();
			            } catch (InputMismatchException e) {
			                System.out.println("Wrong choice.\n Enter again.");
			            }
			            sc.nextLine(); // clears the buffer
			        } while (uCh <= 0);
					
					//switch for operations
					switch(uCh)
					{
						case 1:// for service request ID
							System.out.println(" >> Enter Service Request ID:");
							int requestID=0;
					
							do {
					            try {
					            	requestID = sc.nextInt();
					            } catch (InputMismatchException e) {
					                System.out.println("Wrong input.\n Enter again.");
					            }
					            sc.nextLine(); // clears the buffer
					        } while (requestID <= 0);
							
							List<ServiceTracker> serTracker = bSer.getServiceRequestById(userName, requestID);//check in the list 
							if(serTracker.isEmpty())
							{
								System.out.println("!! Alert: Entered wrong Service Request ID !!");
							}
							else
							{
								System.out.println("Service ID\tAccount ID\tStatus\tDescription");
								for(ServiceTracker s:serTracker)
								{
									System.out.println(s.getService_ID()+"\t\t"+s.getAccount_ID()+"\t"+s.getService_status()+"\t"+s.getService_Description());
								}
								homeLogger.info(" User requested for services request for Service ID "+requestID);
							}
							break;
						case 2://to show the list of service request
							System.out.println(" You would like to view the services requested by you for A/c No ?");
							acChoice=bSer.getCurrentAcNo(userName);
							List<ServiceTracker> serviceRqst=bSer.getAllServiceRequested(acChoice);
							if(!serviceRqst.isEmpty())
							{
								System.out.println("Service ID\tAccount ID\tStatus\tDescription");
								for(ServiceTracker s:serviceRqst)
								{
									System.out.println(s.getService_ID()+"\t\t"+s.getAccount_ID()+"\t\t"+s.getService_status()+"\t"+s.getService_Description());
								}

								homeLogger.info("User requested for services request by him/her for A/c no. "+acChoice);
							}
							else
								System.out.println("You have made no request till now.");
							break;
						default:
							System.out.println("!! Alert: Invalid option! Try again...");
					}
					break;
					
					
				case 5://fund transaction
					int fromAcChoice,toAcChoice =0,amt;
					String tnxPassword;
					System.out.println(" You would like to transfer fund to your own account or to others ?\n press 1:transfer fund to self\n press 2:transfer fund to others");
					
					int uc=0;
					do {
			            try {
			            	uc = sc.nextInt();
			            } catch (InputMismatchException e) {
			                System.out.println("Wrong choice.\n Enter again.");
			            }
			            sc.nextLine(); // clears the buffer
			        } while (uc <= 0);
					
					
					switch (uc) 
					{
						case 1:
							
							System.out.println(" >> Enter your beneficiary account");
							toAcChoice=bSer.getCurrentAcNo(userName);
							do
							{
								System.out.println(" You would like to transfer ammount from?");
								fromAcChoice=bSer.getCurrentAcNo(userName);
								if(toAcChoice==fromAcChoice)
									System.out.println("!! Alert: You have selected same Account no.!!");
							}while(toAcChoice==fromAcChoice);
							System.out.println(" >> Enter fund amount you want to transfer");
							amt=sc.nextInt();
							System.out.println(" >> Enter the transaction password");
							
							tnxPassword=sc.next();
							while(!bSer.checkTransactionPassword(userName,tnxPassword))
							{
									System.out.println("!! Alert: Wrong transaction password !!\n Enter valid transaction password");
									tnxPassword=sc.next();
							}
							bSer.checkBalanceAndMakeTransaction(toAcChoice,fromAcChoice,amt);
							break;
						case 2:
							System.out.println("Press 1 to Add a new Payee\nPress 2 to Make a Transfer using Registered Payee a/c");
						
							int c=0;
							do {
					            try {
					            	c = sc.nextInt();
					            } catch (InputMismatchException e) {
					                System.out.println("Wrong choice.\n Enter again.");
					            }
					            sc.nextLine(); // clears the buffer
					        } while (c <= 0);
							
							
							switch(c)
							{
								case 1:
									System.out.println(bSer.validateAndCreatePayeeAccount(userName));
									break;
								case 2:
									List<PayeeTable> payeeList = bSer.getPayeeAccountId(userName);
									if(!payeeList.isEmpty())
									{
										List<Integer> payeeAcs = new ArrayList<Integer>();
										System.out.println("Payee A/c No. \tNick Name");
										for(PayeeTable p:payeeList)
										{
											System.out.println(p.getPayee_Account_Id()+"\t\t"+p.getNick_name());			
											payeeAcs.add(p.getPayee_Account_Id());
										}
										do
										{
											System.out.println(" >> Enter a Registered Payee Account:");
											
											
											do {
									            try {
									            	toAcChoice = sc.nextInt();
									            } catch (InputMismatchException e) {
									                System.out.println("Wrong choice.\n Enter again.");
									            }
									            sc.nextLine(); // clears the buffer
									        } while (toAcChoice <= 0);
											
											
											
										}while(!payeeAcs.contains(toAcChoice));
										System.out.println("? You would like to transfer ammount from");
										fromAcChoice=bSer.getCurrentAcNo(userName);
										System.out.println(" >> Enter fund amount you want to transfer");
										amt=sc.nextInt();
										System.out.println(" >>Enter the transaction password");
										tnxPassword=sc.next();
										while(!bSer.checkTransactionPassword(userName,tnxPassword))
										{
												System.out.println("!! Alert: Wrong transaction password !!\n Enter valid transaction password");
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
					while(!bSer.checkOldPassword(userName,oldPass))
					{
							System.out.println("!! Alert: Wrong old password !!\n Enter valid password");
							oldPass=sc.next();
					}
					
					
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
							System.out.println("!! Alert: New Password and Confirm Password did not matched !!");
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