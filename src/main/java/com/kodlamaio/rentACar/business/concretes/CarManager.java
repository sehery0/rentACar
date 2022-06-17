package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.request.brands.CreateBrandRequest;
import com.kodlamaio.rentACar.business.request.cars.CreateCarRequest;
import com.kodlamaio.rentACar.business.request.cars.DeleteCarRequest;
import com.kodlamaio.rentACar.business.request.cars.UpdateCarRequest;
import com.kodlamaio.rentACar.business.response.cars.GetAllCarResponse;
import com.kodlamaio.rentACar.business.response.cars.GetCarResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.entities.concretes.Brand;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.Color;

import net.bytebuddy.asm.Advice.This;

@Service
public class CarManager implements CarService {

	
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CarManager(CarRepository carRepository, ModelMapperService modelMapperService) {
		super();
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
	}

	
	
	@Override
	public Result add(CreateCarRequest createCarRequest) {
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		car.setState(1);	
		
		List<Car> existsCars=carRepository.getByBrandId(car.getBrand().getId());
		checkNumberOfCars(existsCars);
		/*if (maxBrand(createCarRequest.getBrand_id())) {
			this.carRepository.save(car);
			return new SuccessResult("CAR.ADDED");
		} else {
			return new ErrorResult("Not");
		}*/
		this.carRepository.save(car);
		return new SuccessResult("CAR.ADDED");
	
	}
	
	

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		car.setState(1);
		this.carRepository.save(car);
		return new SuccessResult("CAR.UPDATED");

	}

	@Override
	public Result delete(int id) {	
				this.carRepository.deleteById(id);
		return new SuccessResult("CAR.DELETED");
	}

	@Override
	public DataResult<List<GetAllCarResponse>> getAll() {
		List<Car> cars = this.carRepository.findAll();
		List<GetAllCarResponse> response = cars.stream()
				.map(car -> modelMapperService.forResponse().map(car, GetAllCarResponse.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllCarResponse>>(response);
	}


	@Override
	public DataResult<GetCarResponse> getById(int id) {
		Car car = this.carRepository.findById(id);
		GetCarResponse response = this.modelMapperService.forResponse().map(car, GetCarResponse.class);
		return new SuccessDataResult<GetCarResponse>(response);
	
	}
	
	private void checkNumberOfCars(List<Car> cars) 
	{
		if(cars.size()<5) 
			throw new BusinessException("CAR.EXIST");
			
	}

	private boolean maxBrand(int brandId) {
		boolean exits = false;
		if (carRepository.getByBrandId(brandId).size() < 5) {
			exits = true;
		} else {
			throw new BusinessException("CAR.EXITS");
		}
		return exits;
	}
}

