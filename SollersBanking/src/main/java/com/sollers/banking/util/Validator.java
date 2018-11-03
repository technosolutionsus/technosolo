package com.sollers.banking.util;

public class Validator {
	private static Validator instance = null;

	public static Validator getInstance() {
		if (instance == null) {
			instance = new Validator();
		}
		return instance;
	}

	
}