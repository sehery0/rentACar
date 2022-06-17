package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.additionalServiceItems.UpdateAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.request.additionalServices.CreateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.request.additionalServices.UpdateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.response.additionalServices.AdditionalServiceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AdditionalServiceService {
	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest);
	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest);
	Result delete(int id);
	DataResult<List<AdditionalServiceResponse>>  findAllByRentalId(int rentalId);

}
