package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AdditionalService;
import com.kodlamaio.rentACar.business.request.additionals.CreateAdditionalRequest;
import com.kodlamaio.rentACar.business.request.additionals.UpdateAdditionalRequest;
import com.kodlamaio.rentACar.business.request.brands.DeleteBrandRequest;
import com.kodlamaio.rentACar.business.response.additionals.GetAdditionalResponse;
import com.kodlamaio.rentACar.business.response.additionals.GetAllAdditionalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionals")
public class AdditionalsController {
	private AdditionalService additionalService;

	public AdditionalsController(AdditionalService additionalService) {
		super();
		this.additionalService= additionalService;
	}
	
	@PostMapping("/add")
	public Result add(CreateAdditionalRequest createAdditionalRequest) {
		return this.additionalService.add(createAdditionalRequest);
	}
	
	@PostMapping("/update")
	public Result update(UpdateAdditionalRequest updateAdditionalRequest) {
		return this.additionalService.update(updateAdditionalRequest);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@RequestBody int id) {
		return this.additionalService.delete(id);
	}
	
	@GetMapping("/getById")
	public DataResult<GetAdditionalResponse> getById(@RequestParam int id) {
		return additionalService.getById(id);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<GetAllAdditionalResponse>> getAll() {
		return this.additionalService.getAll();
	}

}
