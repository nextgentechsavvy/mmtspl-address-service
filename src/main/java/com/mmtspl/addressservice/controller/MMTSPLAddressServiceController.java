package com.mmtspl.addressservice.controller;


import java.util.List;
import javax.transaction.Transactional;
import com.mmtspl.addressservice.model.Address_Master;
import com.mmtspl.addressservice.service.AddressService;
import com.mmtspl.addressservice.model.Address_Master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = {"${settings.cors_origin}"})
@CrossOrigin(origins = {"${settings.cors_origin_localhost}", "${settings.cors_origin_localhost_global}", "${settings.cors_origin}"})
@RestController
@RequestMapping(value="/restapiaddressservices")
public class MMTSPLAddressServiceController {
	
	@Autowired
	private AddressService addressService;
	
	//********************************* Address_Master Start *********************************************//
	
	@GetMapping("/addressservices")
	public String display() {
		return "Address Services returns Hiiii...";
	}

	// ****************** Calling from FrontController ********************** //

	@Transactional
	@GetMapping("/getAllAddress")
	public List<Address_Master> getAllAddress() {
		return addressService.getAllAddress();
	}

	@Transactional
	@GetMapping("/getAddressById/{addressId}")
	public Address_Master getAddressById(@PathVariable int addressId) {
		return addressService.getAddressById(addressId);
	}
	
	@Transactional
	@PostMapping("/addAddress")
	public Address_Master addAddress(@RequestBody Address_Master address) {
		return addressService.addAddress(address);
	}

	@Transactional
	@PutMapping("/updateAddress")
	public void updateAddress(@RequestBody Address_Master address) {
		System.out.println("updateAddress..");
		addressService.updateAddress(address);
	}

	@Transactional
	@PutMapping("/updateAddressById/{addressId}")
	public void updateAddressById(@RequestBody Address_Master address, @PathVariable int addressId) {
		addressService.updateAddressById(address, addressId);
	}

	@Transactional
	//@DeleteMapping("/deleteAddress/{id}")
	@GetMapping("/deleteAddress/{addressId}")
	public boolean deleteAddress(@PathVariable int addressId) {
		return addressService.deleteAddress(addressId);
	}
	
	// ****************** @NamedQueries ********************** //
	@Transactional
	@GetMapping("/getAddressByCity/{city}")
	public List<Address_Master> getAddressByCity(@PathVariable String city) {
		return addressService.getAddressByCity(city.trim());
	}

	@Transactional
	@GetMapping("/getAllEmployeeID")
	public List<Integer> getAllEmployeeID() {
		return addressService.getAllEmployeeID();
	}
	
	@Transactional
	@GetMapping("/getAddressByEmployeeID/{employeeId}")
	public List<Address_Master> getAddressByEmployeeID(@PathVariable int employeeId) {
		return addressService.getAddressByEmployeeID(employeeId);
	}

	@Transactional
	@GetMapping("/getAddressByEmployeeIDAddressType/{employeeId}/{addressType}")
	public List<Address_Master> getAddressByEmployeeIDAddressType(@PathVariable int employeeId, @PathVariable String addressType) {
		return addressService.getAddressByEmployeeIDAddressType(employeeId,addressType);
	}
	
	@Transactional
	@GetMapping("/getAddressByAddressType/{addressType}")
	public List<Address_Master> getAddressByAddressType(@PathVariable String addressType) {
		return addressService.getAddressByAddressType(addressType);
	}

	// ****************** Calling from FrontController ********************** //

	
	

	//********************************* Address_Master End *********************************************//
	
	
}
