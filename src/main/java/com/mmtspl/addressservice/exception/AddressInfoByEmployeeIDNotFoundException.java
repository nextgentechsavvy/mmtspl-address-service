package com.mmtspl.addressservice.exception;

public class AddressInfoByEmployeeIDNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AddressInfoByEmployeeIDNotFoundException(int employeeId) {

		super(String.format("Address with employeeId %d not found", employeeId)); // Displayed message on Console for reference
	}
}
