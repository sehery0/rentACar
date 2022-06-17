package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.abstracts.CityService;
import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.request.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.request.users.CreateUserRequest;
import com.kodlamaio.rentACar.business.response.rentals.GetAllRentalResponse;
import com.kodlamaio.rentACar.business.response.rentals.GetRentalResponse;
import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.FindexCheckService;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CityRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.UserRepository;
import com.kodlamaio.rentACar.entities.concretes.Brand;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.City;
import com.kodlamaio.rentACar.entities.concretes.Rental;
import com.kodlamaio.rentACar.entities.concretes.User;

import net.bytebuddy.asm.Advice.This;

@Service
public class RentalManager implements RentalService {


	private RentalRepository rentalRepository;
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	private CityRepository cityRepository;
	private UserRepository userRepository;
	private FindexCheckService findexCheckService;
	
	@Autowired
	public RentalManager(RentalRepository rentalRepository, CarRepository carRepository,
			ModelMapperService modelMapperService, CityRepository cityRepository, UserRepository userRepository,
			FindexCheckService findexCheckService) {
		this.rentalRepository = rentalRepository;
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
		this.cityRepository = cityRepository;
		this.userRepository = userRepository;
		this.findexCheckService = findexCheckService;
	}
	

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		Car car = this.carRepository.findById(createRentalRequest.getCarId());
		City pickUpCity = cityRepository.findById(createRentalRequest.getPickUpCityId());
		City returnedCity = cityRepository.findById(createRentalRequest.getReturnedCityId());
		car.setState(3);
		User userId = userRepository.findById(createRentalRequest.getUserId());
		
		LocalDate start = createRentalRequest.getPickUpDate();
		LocalDate end = createRentalRequest.getReturnedDate();
		
		Period period = Period.between(start, end);
		int diff = Math.abs(period.getDays());
		rental.setTotalPrice(rental.getTotalDays() * car.getDailyPrice());
		if(!pickUpCity.equals(returnedCity)) {
			rental.setTotalPrice(rental.getTotalDays() * car.getDailyPrice()+750);
	}
		checkFindexMinValue(userId.getIdentityNumber(), car.getCarScore());
		this.rentalRepository.save(rental);
		
		return new SuccessResult("RENTAL.ADDED");
	}

	

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		Car car = carRepository.findById(updateRentalRequest.getCarId());
		car.setId(updateRentalRequest.getCarId());
		
		LocalDate date = LocalDate.now();
		
		//Sorrr?
		/*if (date.equals(rental.getReturnedDate()) || date.isBefore(rental.getPickUpDate()) || date.isAfter(rental.getReturnedDate())) {
			car.setState(1);
		} else {
			car.setState(3);		}*/

		this.rentalRepository.save(rental);
		return new SuccessResult("RENTAL.UPDATED");
	}

	
	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		Rental rental = this.modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
		this.rentalRepository.delete(rental);
		return new SuccessResult("RENTAL.DELETED");
	}
	

	@Override
	public DataResult<GetRentalResponse> getById(int id) {
		Rental rental = this.rentalRepository.findById(id);
		GetRentalResponse response = this.modelMapperService.forResponse().map(rental, GetRentalResponse.class);
		return new SuccessDataResult<GetRentalResponse>(response);
	}
	

	@Override
	public DataResult<List<GetAllRentalResponse>> getAll() {
		
		List<Rental> rentals = this.rentalRepository.findAll();
		List<GetAllRentalResponse> response = rentals.stream()
				.map(rental ->this.modelMapperService.forResponse()
						.map(rental, GetAllRentalResponse.class)).collect(Collectors.toList());			
		return new SuccessDataResult<List<GetAllRentalResponse>>(response);
	}
	
	private void checkFindexMinValue(String identityNumber, int carScore) {
		if(findexCheckService.CheckFindexScore(identityNumber) < carScore) {
			throw new BusinessException("RENTAL.NOT.ADDED.FINDEXPOINT.INSUFFICIENT");
		}
	}

}
