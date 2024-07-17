package com.mmtspl.addressservice.exception;

public class AddressInfoByAddressTypeNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public AddressInfoByAddressTypeNotFoundException(String addressType) {
		super(String.format("Address Info with addressType:-   %s   not found", addressType)); // Displayed message on Console for reference
	}

}