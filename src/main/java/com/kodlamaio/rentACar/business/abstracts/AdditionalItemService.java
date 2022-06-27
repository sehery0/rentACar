package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.additionalItems.CreateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.request.additionalItems.UpdateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.response.additionalItems.GetAdditionalItemResponse;
import com.kodlamaio.rentACar.business.response.additionalItems.GetAllAdditionalItemResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AdditionalItemService {
	Result add(CreateAdditionalItemRequest createAdditionalServiceItemRequest);
	Result update(UpdateAdditionalItemRequest updateAdditionalServiceItemRequest);
	Result delete(int id);
	DataResult<GetAdditionalItemResponse> getById(int id);
	DataResult<List<GetAllAdditionalItemResponse>> getAll();

}
