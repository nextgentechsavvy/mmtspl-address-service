package com.mmtspl.addressservice.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmtspl.addressservice.exception.AddressInfoByAddressTypeNotFoundException;
import com.mmtspl.addressservice.exception.AddressInfoByCityNotFoundException;
import com.mmtspl.addressservice.exception.AddressInfoByEmployeeIDNotFoundException;
import com.mmtspl.addressservice.exception.AddressInfoByIDNotFoundException;
import com.mmtspl.addressservice.exception.NoAddressDataFoundException;
import com.mmtspl.addressservice.model.Address_Master;
import com.mmtspl.addressservice.repository.AddressRepository;



@Service("addressService")
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;
	
	private Boolean matched = false;
	
	// ****************** Calling from FrontController ********************** //

	@Transactional
	public List<Address_Master> getAllAddress() {
		List<Address_Master> addressList = addressRepository.getAllAddress(); 
		
		if(addressList.isEmpty())
			 throw new NoAddressDataFoundException();
		
		return addressList;
	}
	
	@Transactional
	public Address_Master getAddressById(int addressId) {
		
		//return addressRepository.getAddress(id).orElseThrow(() -> new AddressNotFoundException(id));;
		Address_Master address = addressRepository.getAddressById(addressId);
			if(address == null) throw new AddressInfoByIDNotFoundException(addressId);
		return address;
	}
	
	@Transactional
	public Address_Master addAddress(Address_Master address) {
		return addressRepository.addAddress(address);
	}
	
	@Transactional
	public void updateAddress(Address_Master address) {
		addressRepository.updateAddress(address);

	}
	
	@Transactional
	public boolean deleteAddress(int addressId) {
		matched = addressRepository.deleteAddress(addressId);
		if(matched == false) throw new AddressInfoByIDNotFoundException(addressId);
		return matched;
	}
	
	
	
	// ****************** @NamedQueries ********************** //
	@Transactional
	public List<Address_Master> getAddressByCity(String city) {
		
		//return addressRepository.getAddress(id).orElseThrow(() -> new AddressNotFoundException(id));;
		List<Address_Master> addressList = addressRepository.getAddressByCity(city);
			if(addressList == null) throw new AddressInfoByCityNotFoundException(city);
		return addressList;
	}

	@Transactional
	public List<Integer> getAllEmployeeID()  {
		List<Integer> addressList = addressRepository.getAllEmployeeID();
			if(addressList == null) throw new AddressInfoByEmployeeIDNotFoundException(0);
		return addressList;
	}		
	
	@Transactional
	public List<Address_Master> getAddressByEmployeeID(int employeeId)  {
		
		//return addressRepository.getAddress(id).orElseThrow(() -> new AddressNotFoundException(id));;
		List<Address_Master> addressList = addressRepository.getAddressByEmployeeID(employeeId);
			if(addressList == null) throw new AddressInfoByEmployeeIDNotFoundException(employeeId);
		return addressList;
	}

	@Transactional
	public List<Address_Master> getAddressByEmployeeIDAddressType(int employeeId, String addressType) {
		
		//return addressRepository.getAddress(id).orElseThrow(() -> new AddressNotFoundException(id));;
		List<Address_Master> addressList = addressRepository.getAddressByEmployeeIDAddressType(employeeId, addressType);
			if(addressList == null) throw new AddressInfoByEmployeeIDNotFoundException(employeeId);
		return addressList;
	}

	@Transactional
	public List<Address_Master> getAddressByAddressType(String addressType) {
		
		//return addressRepository.getAddress(id).orElseThrow(() -> new AddressNotFoundException(id));;
		List<Address_Master> addressList = addressRepository.getAddressByAddressType(addressType);
			if(addressList == null) throw new AddressInfoByAddressTypeNotFoundException(addressType);
		return addressList;
	}

	// ****************** Calling from FrontController ********************** //

	public String getSubscriptionMessage(String user) {
		return "Hello "+user+", Thanks for the subscription!";
	}
	
	public void updateAddressById(Address_Master address, int addressId) {
		addressRepository.updateAddressById(address, addressId);

	}


}
