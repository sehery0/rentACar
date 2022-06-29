package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.corporatecustomers.CreateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.request.corporatecustomers.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.response.corporateCustomers.GetAllCorporateCustomerResponse;
import com.kodlamaio.rentACar.business.response.corporateCustomers.GetCorporateCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface CorporateCustomerService {
	DataResult<List<GetAllCorporateCustomerResponse>> getAll();
	DataResult<GetCorporateCustomerResponse> findById(int id);
	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
	Result delete(int id);


}
