package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.MaintenanceService;
import com.kodlamaio.rentACar.business.request.maintenances.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.request.maintenances.DeleteMaintenanceRequest;
import com.kodlamaio.rentACar.business.request.maintenances.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.response.maintenances.GetAllMaintenanceResponse;
import com.kodlamaio.rentACar.business.response.maintenances.GetMaintenanceResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.MaintenanceRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.Maintenance;

@Service
public class MaintenanceManager implements MaintenanceService {
	
	
	
	private MaintenanceRepository maintenanceRepository;
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	
	

	@Autowired
	public MaintenanceManager(MaintenanceRepository maintenanceRepository, CarRepository carRepository,
			ModelMapperService modelMapperService) {
		super();
		this.maintenanceRepository = maintenanceRepository;
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {
		Car car = this.carRepository.getById(createMaintenanceRequest.getCarId());
		car.setId(createMaintenanceRequest.getCarId());
		car.setState(2);
		Maintenance maintenance = this.modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);

		this.maintenanceRepository.save(maintenance);
		return new SuccessResult("ADDED.MAINTENANCE");
	}

	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		Maintenance maintenance = this.modelMapperService.forRequest().map(deleteMaintenanceRequest, Maintenance.class);
		this.maintenanceRepository.delete(maintenance);
		return new SuccessResult("MAINTENANCE.DELETED");
	}

	@Override
	public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {
		Car car = this.carRepository.getById(updateMaintenanceRequest.getCarId());
		car.setId(updateMaintenanceRequest.getCarId());
		
		LocalDate localDate = LocalDate.now();

		if (localDate.equals(updateMaintenanceRequest.getDateReturned())) {
			car.setState(1);
		}
		
		Maintenance maintenance = this.modelMapperService.forRequest().map(updateMaintenanceRequest, Maintenance.class);
		this.maintenanceRepository.save(maintenance);

		return new SuccessResult("MAINTENANCE.UPDATED");
	}

	@Override
	public DataResult<GetMaintenanceResponse> getById(int id) {
		Maintenance maintenance = this.maintenanceRepository.findById(id);
		GetMaintenanceResponse response = this.modelMapperService.forResponse().map(maintenance, GetMaintenanceResponse.class);
		return new SuccessDataResult<GetMaintenanceResponse>(response);
	}

	@Override
	public DataResult<List<GetAllMaintenanceResponse>> getAll() {
		List<Maintenance> maintenances = this.maintenanceRepository.findAll();
		List<GetAllMaintenanceResponse> response = maintenances.stream()
				.map(maintenance -> modelMapperService.forResponse().map(maintenance, GetAllMaintenanceResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllMaintenanceResponse>>(response);
	}
}
