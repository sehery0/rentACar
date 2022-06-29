package com.kodlamaio.rentACar.core.utilities.adapters.abstracts;

import java.rmi.RemoteException;

import com.kodlamaio.rentACar.business.request.individualcustomers.CreateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.request.individualcustomers.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;
import com.kodlamaio.rentACar.entities.concretes.User;

public interface PersonCheckService {
	// boolean checkPerson(User user) throws NumberFormatException, RemoteException; 
	boolean checkIfUserExist(IndividualCustomer individualCustomer);

	

}
