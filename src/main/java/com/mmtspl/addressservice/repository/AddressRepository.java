package com.mmtspl.addressservice.repository;


import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.mmtspl.addressservice.exception.AddressInfoByCityNotFoundException;
import com.mmtspl.addressservice.exception.AddressInfoByIDNotFoundException;
import com.mmtspl.addressservice.exception.NoAddressDataFoundException;
import com.mmtspl.addressservice.model.Address_Master;
import com.mmtspl.addressservice.exception.RecordNotFoundNullPointerException;


@Repository
public class AddressRepository {
	
	@Autowired
	SessionFactory sessionFactory;
	
	private String message ="";
	private Boolean matched = false;

	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// ****************** Calling from FrontController ********************** //

	@SuppressWarnings("unchecked")
	public List<Address_Master> getAllAddress() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Address_Master> addressList = null;
		try {
			addressList = session.createQuery("from Address_Master").list();
		}catch(NoAddressDataFoundException naf) {
			naf.printStackTrace();
		}catch(RecordNotFoundNullPointerException rnfnpe) {
			rnfnpe.printStackTrace();
		}finally {
			//session.close();
		}
		return addressList;
	}

	public Address_Master getAddressById(int addressId) {
		Session session = this.sessionFactory.getCurrentSession();
		Address_Master address = null;
		try {
			address = session.get(Address_Master.class, addressId);
		}catch(AddressInfoByIDNotFoundException aibIDnf) {
			aibIDnf.printStackTrace();
		}catch(RecordNotFoundNullPointerException rnfnpe) {
			rnfnpe.printStackTrace();
		}finally {
			//session.close();
		}
		
		return address;
	}
	
	public Address_Master addAddress(Address_Master address) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
		Serializable savedAddressId = session.save(address);
		if(savedAddressId != null)
			System.out.println("Saved Address ID :: " + savedAddressId);
		else
			System.out.println("Address Record Not Saved... ");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return address;
	}

	@Transactional
	public void updateAddress(Address_Master address) {
		Session session = this.sessionFactory.getCurrentSession();
		Hibernate.initialize(address);
		session.update(address);
	}

	@Transactional
	public void updateAddressById(Address_Master address, int AddressId) {
		Session session = this.sessionFactory.getCurrentSession();
		Hibernate.initialize(address);
		session.saveOrUpdate(String.valueOf(AddressId), address);
	}

	public boolean deleteAddress(int addressId) {
		Session session = this.sessionFactory.getCurrentSession();
		//Transaction transaction = session.beginTransaction();
		Address_Master address_master = (Address_Master) session.get(Address_Master.class, addressId);
		
		try {
			if (address_master != null) {
				matched = true;
				System.out.println(address_master.getAddressId());
				session.delete(address_master);
				message = "Deleted Address Record is:" + " \n" + "Address ID: " + address_master.getAddressId() + "  City: " + address_master.getCity() + "  Country: " + address_master.getCountry() + "  State: " + address_master.getState() + "  Locality: " + address_master.getLocality() + "  Zipcode: " + address_master.getZipcode() + "  Address ID: " + address_master.getAddressId() + "\n"; 
			}else{
				matched = false;
				message = "Address with ID : " + addressId + " not found....";
			}
		}catch(AddressInfoByIDNotFoundException enf) {
			enf.printStackTrace();
		}finally {
			//transaction.commit();
		}
		
		System.out.println("Delete message :: " + message);
		return matched;
	}
	
	
	
	
	// ****************** @NamedQueries ********************** //
	@SuppressWarnings("unchecked")
	public List<Address_Master> getAddressByCity(String city) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		//Optional<Address> addressOptional = null;
		List<Address_Master> addressList= null;
		
		try {
				query = session.getNamedQuery("@HQL_Find_Address_By_City");
				query.setParameter("city", city);
				System.out.println("Named Query is : " + query.getQueryString());
				
				addressList = (List<Address_Master>) query.list().stream().collect(Collectors.toList());
				//addressList = session.createQuery(query.toString()).list();
				
				if(addressList.isEmpty())
					addressList = null;
		}catch(AddressInfoByCityNotFoundException aibcnfe) {
			aibcnfe.printStackTrace();
		}finally {
			//session.close();
		}
		
		return addressList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Address_Master> getAddressByEmployeeIDAddressType(int employeeId, String addressType) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		//Optional<Address> addressOptional = null;
		List<Address_Master> addressList= null;
		
		try {
				query = session.getNamedQuery("@HQL_Find_Address_By_EmployeeID_AddressType");
				query.setParameter("employeeId", employeeId);
				query.setParameter("addressType", addressType);
				System.out.println("Named Query is : " + query.getQueryString());
				
				addressList = (List<Address_Master>) query.list().stream().collect(Collectors.toList());
				//addressList = session.createQuery(query.toString()).list();
				
				if(addressList.isEmpty())
					addressList = null;
		}catch(AddressInfoByCityNotFoundException aibcnfe) {
			addressList = null;
			aibcnfe.printStackTrace();
		}finally {
			//session.close();
		}
		
		return addressList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Address_Master> getAddressByAddressType(String addressType) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		//Optional<Address> addressOptional = null;
		List<Address_Master> addressList= null;
		
		try {
				query = session.getNamedQuery("@HQL_Find_Address_By_AddressType");
				query.setParameter("addressType", addressType);
				System.out.println("Named Query is : " + query.getQueryString());
				
				addressList = (List<Address_Master>) query.list().stream().collect(Collectors.toList());
				//addressList = session.createQuery(query.toString()).list();
				
				if(addressList.isEmpty())
					addressList = null;
		}catch(AddressInfoByCityNotFoundException aibcnfe) {
			aibcnfe.printStackTrace();
		}finally {
			//session.close();
		}
		
		return addressList;
	}
	
	// ****************** Calling from FrontController ********************** //

	//------------ Calling from Employee Service --------------------------//

	@SuppressWarnings("unchecked")
	public List<Integer> getAllAddressEmployeeID() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Integer> employeeIDList = null;
		try {
			employeeIDList = session.createQuery("Select employeeId from Address_Master").list();
		}catch(NoAddressDataFoundException naf) {
			naf.printStackTrace();
		}catch(RecordNotFoundNullPointerException rnfnpe) {
			rnfnpe.printStackTrace();
		}finally {
			//session.close();
		}
		return employeeIDList;
	}

	@SuppressWarnings("unchecked")
	public List<Address_Master> getAddressByEmployeeID(int employeeId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		//Optional<Address> addressOptional = null;
		List<Address_Master> addressList= null;

		try {
			query = session.getNamedQuery("@HQL_Find_Address_By_EmployeeID");
			query.setParameter("employeeId", employeeId);
			System.out.println("Named Query is : " + query.getQueryString());

			addressList = (List<Address_Master>) query.list().stream().collect(Collectors.toList());
			//addressList = session.createQuery(query.toString()).list();

			if(addressList.isEmpty())
				addressList = null;
		}catch(AddressInfoByCityNotFoundException aibcnfe) {
			aibcnfe.printStackTrace();
		}finally {
			//session.close();
		}

		return addressList;
	}
	//------------ Calling from Employee Service --------------------------//



}
