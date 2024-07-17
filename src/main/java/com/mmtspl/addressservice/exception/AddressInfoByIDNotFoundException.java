package com.mmtspl.addressservice.exception;

public class AddressInfoByIDNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AddressInfoByIDNotFoundException(int addressId) {

		super(String.format("Address with Id %d not found", addressId)); // Displayed message on Console for reference
	}
}