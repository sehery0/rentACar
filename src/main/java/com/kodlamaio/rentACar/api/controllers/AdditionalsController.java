package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AdditionalService;
import com.kodlamaio.rentACar.business.request.additionals.CreateAdditionalRequest;
import com.kodlamaio.rentACar.business.request.additionals.UpdateAdditionalRequest;
import com.kodlamaio.rentACar.business.response.additionals.GetAllAdditionalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionals")
public class AdditionalsController {
	private AdditionalService additionalService;

	public AdditionalsController(AdditionalService additionalService) {
		super();
		this.additionalService = additionalService;
	}

	@GetMapping("/getallbyrentalid/{id}")
	public DataResult<List<GetAllAdditionalResponse>> findAllByRentalId(@RequestBody int id) {
		return additionalService.findAllByRentalId(id);

	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateAdditionalRequest createAdditionalService) {
		return this.additionalService.add(createAdditionalService);
	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateAdditionalRequest updateAdditionalServiceRequest) {
		return this.additionalService.update(updateAdditionalServiceRequest);
	}

	@DeleteMapping("{id}")
	public Result update(@PathVariable int id) {
		return this.additionalService.delete(id);
	}

}
