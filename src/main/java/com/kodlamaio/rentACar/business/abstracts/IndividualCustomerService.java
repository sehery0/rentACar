package com.kodlamaio.rentACar.business.abstracts;



import java.util.List;

import com.kodlamaio.rentACar.business.request.individualcustomers.CreateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.request.individualcustomers.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.response.individualCustomers.GetAllIndividualCustomerResponse;
import com.kodlamaio.rentACar.business.response.individualCustomers.GetIndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface IndividualCustomerService {
	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
	Result delete(int id);
	String getIdentityNumber(int customerId);
	DataResult<List<GetAllIndividualCustomerResponse>> getAll();
	DataResult<GetIndividualCustomerResponse> findById(int id);

}
