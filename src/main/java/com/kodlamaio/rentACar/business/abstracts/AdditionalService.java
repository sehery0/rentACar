package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.additionalItems.UpdateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.request.additionals.CreateAdditionalRequest;
import com.kodlamaio.rentACar.business.request.additionals.UpdateAdditionalRequest;
import com.kodlamaio.rentACar.business.response.additionals.GetAdditionalResponse;
import com.kodlamaio.rentACar.business.response.additionals.GetAllAdditionalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AdditionalService {
	Result add(CreateAdditionalRequest createAdditionalServiceRequest);
	Result update(UpdateAdditionalRequest updateAdditionalServiceRequest);
	Result delete(int id);
	DataResult<GetAdditionalResponse> getById(int id);
	DataResult<List<GetAllAdditionalResponse>> getAll();

}
