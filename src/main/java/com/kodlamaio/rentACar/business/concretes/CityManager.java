package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CityService;
import com.kodlamaio.rentACar.business.request.cities.CreateCityRequest;
import com.kodlamaio.rentACar.business.request.cities.DeleteCityRequest;
import com.kodlamaio.rentACar.business.request.cities.UpdateCityRequest;
import com.kodlamaio.rentACar.business.response.cities.GetAllCityResponse;
import com.kodlamaio.rentACar.business.response.cities.GetCityResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CityRepository;
import com.kodlamaio.rentACar.entities.concretes.City;

@Service
public class CityManager implements CityService {

	private CityRepository cityRepository;
	private ModelMapperService modelMapperService;

	@Autowired
	public CityManager(CityRepository cityRepository, ModelMapperService modelMapperService) {
		super();
		this.cityRepository = cityRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCityRequest createCityRequest) {
		City city = this.modelMapperService.forRequest().map(createCityRequest, City.class);
		this.cityRepository.save(city);
		return new SuccessResult("CITY.ADDED");
	}

	@Override
	public Result update(UpdateCityRequest updateCityRequest) {
		City city = this.modelMapperService.forRequest().map(updateCityRequest, City.class);
		this.cityRepository.save(city);
		return new SuccessResult("CITY.UPDATED");
	}

	@Override
	public Result delete(DeleteCityRequest deleteCityRequest) {
		City city = this.modelMapperService.forRequest().map(deleteCityRequest, City.class);
		this.cityRepository.delete(city);
		return new SuccessResult("CITY.DELETED");
	}

	@Override
	public DataResult<GetCityResponse> getById(int id) {
		City city = this.cityRepository.findById(id);
		GetCityResponse response = this.modelMapperService.forResponse().map(city, GetCityResponse.class);
		return new SuccessDataResult<GetCityResponse>(response, "CITY.GETTED");
	}

	@Override
	public DataResult<List<GetAllCityResponse>> getAll() {
		List<City> cities = this.cityRepository.findAll();
		List<GetAllCityResponse> response = cities.stream()
				.map(city -> this.modelMapperService.forResponse().map(cities, GetAllCityResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCityResponse>>(response, "CITIES.LISTED");
	}

}
