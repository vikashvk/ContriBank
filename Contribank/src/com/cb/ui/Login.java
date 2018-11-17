package com.cb.ui;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.cb.bean.Customer;
import com.cb.bean.Master;
import com.cb.bean.Transaction;
import com.cb.bean.UserTable;
import com.cb.exception.BankingException;
import com.cb.service.AdminService;
import com.cb.service.AdminServiceImpl;
import com.cb.service.BankingService;
import com.cb.service.BankingServiceImpl;
import com.cb.service.LoginService;
import com.cb.service.LoginServiceImpl;

public class Login {

	static LoginService lser = new LoginServiceImpl();
	static AdminService aser = new AdminServiceImpl();
	static Scanner sc = new Scanner(System.in);
	static BankingService bSer = new BankingServiceImpl();

	public static void main(String args[]) {
		login();
	}

	public static void login() {
		String choice = "";
		System.out.println("\n\n******Welcome To CAPG Bank PVT LTD******");
		do {
			System.out.println(" \n\nSelect your choice: "
					+ "\n  Press 1 to Login"
					+ "\n  Press 2 if you Forgot Password"
					+ "\n  Press 3 to Exit");
			choice = sc.next();
			switch (choice) {

			case "1":
				String role = "";
				do {
					System.out
							.println("You are an Admin/User!"
									+ "\n  Press 1 if you are admin\n  Press 2 if you are User\n  Press 3 to Exit");
					role = sc.next();

					switch (role) {
					case "1":
						Adminlogin();
						break;

					case "2":
						Userlogin();
						break;
					case "3":
						System.exit(0);
					default:
						System.out.println("Invalid Input");
						break;
					}
					break;
				} while (true);
				break;
			case "2":
				String username;
				boolean usrh;
				System.out.println("  >> Enter Username: ");
				username = sc.next();
				usrh = bSer.isUserExist(username);
				if (!usrh)
					System.out.println("!!Alert: No Such User exists.");
				else
					forgotPassword(username);
				break;

			case "3":
				System.out.println("Thank you! Bye.");
				break;
			default:
				System.out.println("!! Alert: Invalid Input!");
				break;
			}

		} while (true);
	}

	// When User Forgets Password check for Secret Answer and provide pwd via
	// email.
	public static void forgotPassword(String username) {
		System.out.println("  >> Answer your Secret Question: ");

		String ans = sc.next();
		String db_ans = lser.checkSecretAns(username);
		if (db_ans.equals(ans)) {
			lser.setpassword(username, "sbq500#");
			System.out
					.println("!! Alert: Password has been sent to your registered email-id.");
		} else
			System.out.println("!! Alert: Invalid User");

	}

	public static void Adminlogin() {
		int Id;
		String password;

		System.out.println(" >> Enter Admin Id: ");
		Id = sc.nextInt();
		System.out.println(" >> Enter Password: ");
		password = sc.next();
		boolean flag = lser.adminLogin(Id, password);
		if (flag) {
			String operation = "";
			do {
				System.out.println("Enter your choice: "
						+ "\n  Press 1 to Create new customer"
						+ "\n  Press 2 to Create account for existing user"
						+ "\n  Press 3 to View Transactions"
						+ "\n  Press 4 to Exit");
				operation = sc.next();
				switch (operation) {
				case "1":
					addNewCustomer();
					break;
				case "2":
					addAccount();
					break;
				case "3":
					getPeriodicalTransaction();
					break;
				case "4":
					System.out.println("Thank you! Bye.");
					login();
				default:
					System.out.println("!! Alert: Invalid Input");
					break;
				}
			} while (true);
		} else
			System.out.println("!! Alert: Invalid Credentials");
	}

	public static void getPeriodicalTransaction() {
		String startDate = "", endDate = "";
		int acno = 0;
		boolean checkst = false, checked = false, acchk = false;
		do {
			System.out.println(" >> Enter A.c No.: ");
			acno = sc.nextInt();
			acchk = bSer.isAccountExist(acno, 0);
			if (!acchk)
				System.out.println("No such account exists");
		} while (!acchk);

		do {
			try {
				System.out.println(" >> Enter End Date in DD-MMM-YY format:");
				startDate = sc.next();
				startDate = startDate.toUpperCase();
				checked = aser.validateDate(startDate);
			} catch (BankingException e) {
				e.getMessage();
			}
		} while ((!checkst));

		do {
			try {
				System.out.println(" >> Enter End Date in DD-MMM-YY format:");
				endDate = sc.next();
				endDate = endDate.toUpperCase();
				checked = aser.validateDate(endDate);

			} catch (BankingException e) {
				e.getMessage();
			}
		} while (!checked);

		List<Transaction> tnx = bSer
				.detailedStatement(acno, startDate, endDate);
		if (!tnx.isEmpty()) {
			System.out.println("Tnx ID\tTnx Date\tType\tAmount\tDescription");
			for (Transaction t : tnx)
				System.out.println(t.getTransaction_ID() + "\t"
						+ t.getDateOfTransaction() + "\t"
						+ t.getTransactionType() + "\t" + t.getTranAmount()
						+ "\t" + t.getTran_description());
		} else
			System.out.println("  No Transaction made by you till now.");
	}

	public static void addAccount() {
		System.out.println("  >> Enter User_Id of existing Customer");
		String userId = sc.next();
		if (aser.chechUser(userId)) {
			accountmaster(userId);
		} else
			System.out
					.println("!! Alert: No such User. Please add user to continue.");
	}

	public static void addNewCustomer() {
		String customer_name = null, Email = null, Address = "", Pancard;
		String login_password;
		boolean namchk = false, mailchk = false;
		do {
			try {
				System.out.println(" >> Enter Customer Name: ");
				customer_name = sc.next();
				namchk = aser.validateCustomerName(customer_name);

			} catch (BankingException e) {
				e.getMessage();
			}
		} while (!namchk);

		do {
			try {
				System.out.println(" >> Enter Email id: ");
				Email = sc.next();
				mailchk = aser.validateEmail(Email);
			} catch (BankingException e) {
				e.getMessage();
			}
		} while (!mailchk);

		System.out.println(" >> Enter Address: ");
		sc.nextLine();
		Address = sc.nextLine();
		System.out.println(" >> Enter Pan Card Number: ");
		Pancard = sc.next();

		login_password = "1asd40#";
		String lock_status = "o";

		Customer cust = new Customer(Pancard, customer_name, Email, Address);
		System.out.println("Success! New Customer added with Customer ID "
				+ aser.addCustomer(cust));

		UserTable ut = new UserTable();
		ut.setLogin_password(login_password);
		ut.setPancard(Pancard);
		ut.setLock_status(lock_status);
		System.out.println("Success! New User added with User ID "
				+ aser.addUser(ut));
		accountmaster(ut.getUser_id());

	}

	public static void accountmaster(String string) {
		String Account_Type = null;
		double Account_Balance = 0;
		boolean acctyp = false, accbal = false;
		do {
			try {
				System.out.println(" >> Enter the Account Type: ");
				Account_Type = sc.next();
				acctyp = aser.validatAccType(Account_Type);
			} catch (BankingException e) {
				e.getMessage();
			}
		} while (!acctyp);

		do {
			try {
				System.out.println(" >> Enter the Account Balance: ");
				Account_Balance = sc.nextDouble();
				accbal = aser.validateMinBal(Account_Balance);
			} catch (BankingException e) {
				e.getMessage();
			}
		} while ((!accbal));

		Calendar currenttime = Calendar.getInstance();
		Date sqldate = new Date((currenttime.getTime()).getTime());
		Master am = new Master();
		am.setAccount_Type(Account_Type);
		am.setAccount_Balance(Account_Balance);
		am.setOpen_date(sqldate);
		am.setUser_ID(string);
		System.out.println("Success! New A/c opened with account No. "
				+ aser.addAccount(am));

		System.out.println("Account successfully created");
	}

	public static void Userlogin() {
		String username;
		String password;
		boolean flag;
		String check;
		String ans, new_pass, tranx_pass;
		// User is given three chances for login.
		for (int i = 1; i <= 3; i++) {
			System.out.println(" >> Enter Username: ");
			username = sc.next();
			System.out.println(" >> Enter Password: ");
			password = sc.next();
			flag = lser.verifyCredentials(username, password);

			// When User-id & Password is correct
			if (flag == true) {

				// Check Lock status of account whether open / blocked
				check = lser.checklockstatus(username, password);

				// When account lock-status is open
				if (check.equals("o")) {
					// Check if password is default forgot password then allow
					// user to change the password.
					if (password.equals("sbq500#")) {
						System.out.println(" >> Enter new Login password: ");
						new_pass = sc.next();
						boolean passchk = false;
						do {
							try {
								passchk = lser.validatePassword(new_pass);
							} catch (BankingException e) {
								e.getMessage();
							}
						} while (!passchk);

						System.out.println(" >> Confirm new password: ");
						String new_pass_conf = sc.next();

						// Confirm the new entered Password
						if (new_pass.equals(new_pass_conf)) {
							lser.setpassword(username, new_pass);
							System.out.println("Success! Passowrd updated");
						} else {
							System.out
									.println("!! Alert: Password did not match");
							continue;
						}
						break;
					}

					else if (password.equals("1asd40#")) {
						UserTable ut = new UserTable();

						boolean sec = false, passchk = false, txnchk = false;
						do {
							System.out
									.println(" >> Enter Secret Question's Answer: ");
							ans = sc.next();
							try {
								sec = lser.validateSecretAns(ans);
							} catch (BankingException e) {
								e.getMessage();
							}
						} while (!sec);
						ut.setSecret_question(ans);

						do {
							System.out
									.println(" >> Enter new Login password: ");
							new_pass = sc.next();
							try {
								passchk = lser.validatePassword(new_pass);
							} catch (BankingException e) {
								e.getMessage();
							}
						} while (!passchk);

						System.out.println(" >> Confirm new Login password: ");
						String new_pass_conf = sc.next();

						do {
							System.out
									.println(" >> Enter new Transaction password: ");
							tranx_pass = sc.next();
							try {
								txnchk = lser.validatePassword(tranx_pass);
							} catch (BankingException e) {
								e.getMessage();
							}
						} while (!txnchk);
						System.out
								.println(" >> Confirm new Transaction password: ");
						String new_tranx_conf = sc.next();
						// Confirm the new entered Password
						if (new_pass.equals(new_pass_conf)
								&& tranx_pass.equals(new_tranx_conf)) {
							ut.setLogin_password(new_pass);
							ut.setTransaction_password(tranx_pass);
							lser.updateUser(ut, username);
							System.out.println("Success! Account Updated");
							break;
						} else {
							System.out
									.println("!! Alert: Password did not match");
							continue;
						}
					}

					// User-id & Password is correct.
					else {
						System.out.println("******Welcome " + username
								+ " to Banking System******");
						Home uh = new Home();
						uh.UserHome(username);
						break;
					}
				}

				// When account lock-status is blocked.
				else {
					System.out
							.println("Oops! Your Account is Locked. Please contact your bank");
					break;
				}

			}

			// Failed attempts of login.
			else if (i <= 2) {
				System.out.println("!! Alert: Invalid User Name or Password");
				continue;
			}

			// Three wrong attempts will block the account.
			else {
				lser.lockAccount(username);
				System.out
						.println("!!Blocked: 3 wrong attempts. Account is locked.");
				break;
			}

		}

	}

}
