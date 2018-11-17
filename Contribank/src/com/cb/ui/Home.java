package com.cb.ui;

import java.util.Scanner;

import com.cb.exception.BankingException;
import com.cb.service.AdminService;
import com.cb.service.AdminServiceImpl;
import com.cb.service.LoginService;
import com.cb.service.LoginServiceImpl;

public class Home {
	static LoginService lService = new LoginServiceImpl();
	static AdminService aService = new AdminServiceImpl();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		login();
	}

	private static void login() {
		int choice;
		System.out.println("\n\n******Welcome To ContriBank PVT LTD******");
		do {
			System.out.println(" \n\nSelect your choice: "
					+ "\n  Press 1 to Login"
					+ "\n  Press 2 if you Forgot Password"
					+ "\n  Press 3 to Exit");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				int role;
				do {
					System.out
							.println("You are an Admin/User?"
									+ "\n  Press 1 if you are Admin\n  Press 2 if you are User\n  Press 3 to Exit");
					role = sc.nextInt();

					switch (role) {
					case 1:
						Adminlogin();
						break;

					case 2:
						Userlogin();
						break;
					case 3:
						System.exit(0);
					default:
						System.out.println("Invalid Input");
						break;
					}
					break;
				} while (true);
				break;

			case 2:

				/*
				 * String username; boolean usrh;
				 * System.out.println("  >> Enter Username: "); username =
				 * sc.next(); usrh = bSer.isUserExist(username); if (!usrh)
				 * System.out.println("!!Alert: No Such User exists."); else
				 * forgotPassword(username); ;
				 */
				break;

			case 3:
				System.out.println("Thank you! Bye.");
				break;

			default:
				System.out.println("!! Alert: Invalid Input!");
				break;
			}

		} while (true);

	}

	/*
	 * private static void forgotPassword(String username) {
	 * System.out.println("  >> Answer your Secret Question: ");
	 * 
	 * String ans = sc.next(); String db_ans = lser.checkSecretAns(username); if
	 * (db_ans.equals(ans)) { lser.setpassword(username, "sbq500#"); System.out
	 * .
	 * println("!! Alert: Password has been sent to your registered email-id.");
	 * } else System.out.println("!! Alert: Invalid User"); }
	 */

	private static void Userlogin() {
		// TODO Auto-generated method stub

	}

	private static void Adminlogin() {
		int adminId;
		String adminPassword;
		System.out.println(" >> Enter Admin Id: ");
		adminId = sc.nextInt();
		System.out.println(" >> Enter Password: ");
		adminPassword = sc.next();
		boolean flag = lService.adminLogin(adminId, adminPassword);
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
					// getPeriodicalTransaction();
					break;
				case "3":
					System.out.println("Back to Login Page..");
					login();
					break;
				default:
					System.out.println("!! Alert: Invalid Input");
					break;
				}
			} while (true);
		}
	}

	private static void addNewCustomer() {
		String name, address, emailId, phNumber, accountType, pancard;
		double openingBal;
		long accountNum;
		boolean nameCheck = false, mailCheck = false;
		do {
//			try {
				System.out.println(" >> Enter Account Holder Name: ");
				name = sc.next();
				nameCheck = aService.validateCustomerName(name);

	/*		} catch (BankingException e) {
				e.getMessage();
			}*/} 
		while (!nameCheck);
		System.out.println(" >> Enter Address: ");
		address = sc.nextLine();

		System.out.println(" >> Enter Phone Number: ");
		phNumber = sc.nextLine();

		do {
//			try {
				System.out.println(" >> Enter Email id: ");
				emailId = sc.next();
				mailCheck = aService.validateEmail(emailId);
//			} catch (BankingException e) {
//				e.getMessage();
//			}
		} while (!mailCheck);

		System.out.println(" >> Enter Account Type: ");
		accountType = sc.nextLine();

		System.out.println(" >> Enter Opening Balance: ");
		openingBal = sc.nextDouble();

		System.out.println(" >> Enter Pancard: ");
		pancard = sc.next();

		
	}
}
