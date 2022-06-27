package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.request.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.UpdateRentalRequest;
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
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.City;
import com.kodlamaio.rentACar.entities.concretes.Rental;
import com.kodlamaio.rentACar.entities.concretes.User;

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
		super();
		this.rentalRepository = rentalRepository;
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
		this.cityRepository = cityRepository;
		this.userRepository = userRepository;
		this.findexCheckService = findexCheckService;
		
	}
	
	

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		//
		checkIfCarState(createRentalRequest.getCarId());
		checkDateToRentACar(createRentalRequest.getPickUpDate(), createRentalRequest.getReturnDate());
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		
		int diffDate = (int) ChronoUnit.DAYS.between(rental.getPickUpDate(), rental.getReturnDate());
		rental.setTotalDays(diffDate);
		
		Car car = this.carRepository.findById(createRentalRequest.getCarId());
		double totalPrice = calculateTotalPrice(rental, car.getDailyPrice());
		car.setState(3);
		
		rental.setTotalPrice(totalPrice);

		User userId = userRepository.findById(createRentalRequest.getUserId());
		
		
		
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

		this.rentalRepository.save(rental);
		return new SuccessResult("RENTAL.UPDATED");
	}

	
	@Override
	public Result delete(int id) {
		this.rentalRepository.deleteById(id);
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
	private void checkIfCarState(int id) {
		Car car = this.carRepository.getById(id);
		if (car.getState() == 2 || car.getState() == 3) {
			throw new BusinessException("CAR.IS.NOT.AVAIBLE");
		}
	}

	private void checkDateToRentACar(LocalDate pickUpDate, LocalDate returnDate) {
		if (!pickUpDate.isBefore(returnDate) || pickUpDate.isBefore(LocalDate.now())) {
			throw new BusinessException("PICKUPDATE.AND.RETURNDATE.ERROR");
		}
	}

	private double isDiffReturnCityFromPickUpCity(int pickUpCity, int returnCity) {
		if (pickUpCity != returnCity) {
			return 750.0;
		}
		return 0;
	}

	private double calculateTotalPrice(Rental rental, double dailyPrice) {
		double days = rental.getTotalDays();
		double totalDailyPrice =  days * dailyPrice;
		double diffCityPrice =  isDiffReturnCityFromPickUpCity(rental.getPickUpCityId().getId(), rental.getReturnCityId().getId());
		double totalPrice = totalDailyPrice + diffCityPrice;
		return totalPrice;
	}
	
	
	

}
