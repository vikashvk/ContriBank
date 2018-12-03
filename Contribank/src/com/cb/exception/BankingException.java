package com.cb.exception;

public class BankingException extends Exception {
	public BankingException(String message) {
		System.out.println(message);
	}
}
