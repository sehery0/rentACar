package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.request.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.request.rentals.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.response.rentals.GetAllRentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
	private RentalService rentalService;

	public RentalsController(RentalService rentalService) {

		this.rentalService = rentalService;
	}

	@PostMapping("/addForIndividualCustomer")
	public Result addForIndividualCustomer(@RequestBody CreateRentalRequest createRentalRequest) {
		return rentalService.addForIndividualCustomer(createRentalRequest);

	}

	@PostMapping("/addForCorporateCustomer")
	public Result addForCorporateCustomer(@RequestBody CreateRentalRequest createRentalRequest) {
		return rentalService.addForCorporateCustomer(createRentalRequest);

	}

	@GetMapping("/getall")
	public DataResult<List<GetAllRentalResponse>> getAll() {
		return rentalService.getAll();
	}

	@DeleteMapping("/{id}")
	public Result delete(@RequestBody int id) {
		return rentalService.delete(id);
	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateRentalRequest updateRentalRequest) {
		return rentalService.update(updateRentalRequest);
	}
}
