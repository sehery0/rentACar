package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.response.rentals.GetAllRentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface RentalService {
Result addForIndividualCustomer(CreateRentalRequest createRentalRequest);
	
	Result addForCorporateCustomer(CreateRentalRequest createRentalRequest);

	DataResult<List<GetAllRentalResponse>> getAll();

	Result delete(int id);

	Result update(UpdateRentalRequest updateRentalRequest);

}
