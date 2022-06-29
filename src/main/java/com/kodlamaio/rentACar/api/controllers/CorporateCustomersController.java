package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.CorporateCustomerService;
import com.kodlamaio.rentACar.business.request.corporatecustomers.CreateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.request.corporatecustomers.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.response.corporateCustomers.GetAllCorporateCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/corporateCustomers")
public class CorporateCustomersController {
	private CorporateCustomerService corporateCustomerService;
	
	public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
		this.corporateCustomerService = corporateCustomerService;
	}

	@GetMapping("/getall")
	public DataResult<List<GetAllCorporateCustomerResponse>> getAll()
	{
		return corporateCustomerService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateCorporateCustomerRequest createCorporateCustomerRequest)
	{
		return corporateCustomerService.add(createCorporateCustomerRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateCorporateCustomerRequest updateCorporateCustomerRequest)
	{
		return corporateCustomerService.update(updateCorporateCustomerRequest);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@RequestBody int id)
	{
		return corporateCustomerService.delete(id);
	}

}
