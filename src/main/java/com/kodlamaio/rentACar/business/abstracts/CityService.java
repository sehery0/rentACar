package com.kodlamaio.rentACar.business.abstracts;


import java.util.List;

import com.kodlamaio.rentACar.business.request.cities.CreateCityRequest;
import com.kodlamaio.rentACar.business.request.cities.DeleteCityRequest;
import com.kodlamaio.rentACar.business.request.cities.UpdateCityRequest;
import com.kodlamaio.rentACar.business.response.cars.GetCarResponse;
import com.kodlamaio.rentACar.business.response.cities.GetAllCityResponse;
import com.kodlamaio.rentACar.business.response.cities.GetCityResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface CityService {
	Result add(CreateCityRequest createCityRequest);
	Result update(UpdateCityRequest updateCityRequest);
	Result delete(DeleteCityRequest deleteCityRequest);
	DataResult<GetCityResponse> getById(int id);
	DataResult<List<GetAllCityResponse>> getAll();

}
