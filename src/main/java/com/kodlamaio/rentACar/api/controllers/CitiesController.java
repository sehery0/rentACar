package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.CityService;
import com.kodlamaio.rentACar.business.request.cities.CreateCityRequest;
import com.kodlamaio.rentACar.business.request.cities.DeleteCityRequest;
import com.kodlamaio.rentACar.business.request.cities.UpdateCityRequest;
import com.kodlamaio.rentACar.business.response.cities.GetAllCityResponse;
import com.kodlamaio.rentACar.business.response.cities.GetCityResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
	
	private CityService cityService;
	
	public CitiesController(CityService cityService) {
		super();
		this.cityService = cityService;
	}


	@PostMapping("/add")
	public Result add(@RequestBody CreateCityRequest createCityRequest) {
	return this.cityService.add(createCityRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateCityRequest updateCityRequest) {
	return this.cityService.update(updateCityRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteCityRequest deleteCityRequest) {
	return this.cityService.delete(deleteCityRequest);
	}
	
	@GetMapping("/getById")
	public DataResult<GetCityResponse> getById(int id) {
		return this.cityService.getById(id);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<GetAllCityResponse>> getAll() {
	return this.cityService.getAll();
	}

	
}
