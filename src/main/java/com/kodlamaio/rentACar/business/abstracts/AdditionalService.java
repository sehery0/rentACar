package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.additionals.CreateAdditionalRequest;
import com.kodlamaio.rentACar.business.request.additionals.UpdateAdditionalRequest;
import com.kodlamaio.rentACar.business.response.additionals.GetAllAdditionalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AdditionalService {
	DataResult<List<GetAllAdditionalResponse>> findAllByRentalId(int rentalId);
	Result add(CreateAdditionalRequest createAdditionalRequest);
	Result update(UpdateAdditionalRequest updateAdditionalRequest);
	Result delete(int id);

}
