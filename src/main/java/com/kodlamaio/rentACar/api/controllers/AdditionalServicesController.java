package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AdditionalServiceService;
import com.kodlamaio.rentACar.business.request.additionalServices.CreateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.request.additionalServices.UpdateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.request.brands.DeleteBrandRequest;
import com.kodlamaio.rentACar.business.response.additionalServices.AdditionalServiceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionalservices")
public class AdditionalServicesController {
	private AdditionalServiceService additionalServiceService;

	public AdditionalServicesController(AdditionalServiceService additionalServiceService) {
		super();
		this.additionalServiceService = additionalServiceService;
	}
	
	@PostMapping("/add")
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		return this.additionalServiceService.add(createAdditionalServiceRequest);
	}
	
	@PostMapping("/update")
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		return this.additionalServiceService.update(updateAdditionalServiceRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestParam int id) {
		return this.additionalServiceService.delete(id);
	}
	
	@GetMapping("/getAllByRentalId/{id}")
	public DataResult<List<AdditionalServiceResponse>> findAllByRentalId(@PathVariable int id) {
		return additionalServiceService.findAllByRentalId(id);
	}
	

}
