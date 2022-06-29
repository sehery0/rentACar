package com.kodlamaio.rentACar.core.utilities.adapters.concretes;

import java.rmi.RemoteException;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.request.individualcustomers.CreateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.request.individualcustomers.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.PersonCheckService;
import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisKpsAdapter implements PersonCheckService {

	/*
	@Override
	public boolean checkPerson(User user) throws NumberFormatException, RemoteException {
		KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();
		boolean result = kpsPublicSoapProxy.TCKimlikNoDogrula(Long.parseLong(user.getIdentityNumber()), user.getFirstName().toUpperCase(), user.getLastName().toUpperCase(), user.getBirthDate().getYear());
		return result;
	}*/
	
	@Override
	public boolean checkIfUserExist(IndividualCustomer individualCustomer) {
		boolean result=false;
		KPSPublicSoapProxy kpsPublicSoapProxy=new KPSPublicSoapProxy();
		try {
			result=kpsPublicSoapProxy.TCKimlikNoDogrula(Long.parseLong(individualCustomer.getIdentityNumber()), individualCustomer.getFirstName(), 
					individualCustomer.getLastName(), individualCustomer.getBirthDate().getYear());
		} catch (NumberFormatException e) {
			System.out.println("number format exception");
			e.printStackTrace();
		} catch (RemoteException e) {
			System.out.println("remote exception");
			e.printStackTrace();
		}
		return result;
	}

}
