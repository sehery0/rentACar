package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.cars.CreateCarRequest;
import com.kodlamaio.rentACar.business.request.cars.UpdateCarRequest;
import com.kodlamaio.rentACar.business.response.cars.GetAllCarResponse;
import com.kodlamaio.rentACar.business.response.cars.GetCarResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Car;

public interface CarService {

	Result add(CreateCarRequest createCarRequest);

	DataResult<List<GetAllCarResponse>> getAll();

	Result deleteById(int id);

	Result update(UpdateCarRequest updateCarRequest);

	DataResult<GetCarResponse> getById(int id);
	
	public Car getCarById(int carId);
}
