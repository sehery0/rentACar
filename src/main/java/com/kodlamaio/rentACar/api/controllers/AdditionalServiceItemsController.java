package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kodlamaio.rentACar.business.abstracts.AdditionalServiceItemService;
import com.kodlamaio.rentACar.business.request.additionalServiceItems.CreateAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.request.additionalServiceItems.UpdateAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.response.additionalServiceItems.AdditionalServiceItemResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public class AdditionalServiceItemsController {
	private AdditionalServiceItemService additionalServiceItemService;

	public AdditionalServiceItemsController(AdditionalServiceItemService additionalServiceItemService) {
		super();
		this.additionalServiceItemService = additionalServiceItemService;
	}
	
	
	@GetMapping("/getall")
	public DataResult<List<AdditionalServiceItemResponse>> getAll()
	{
		return additionalServiceItemService.getAll();
	}

	@GetMapping("/{id}")
	public DataResult<AdditionalServiceItemResponse> getById(@PathVariable int id)
	{
		return additionalServiceItemService.findById(id);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateAdditionalServiceItemRequest createAdditionalServiceItemRequest) {
		return additionalServiceItemService.add(createAdditionalServiceItemRequest);
	}
	
	@PostMapping("/update")
	public Result add(@RequestBody UpdateAdditionalServiceItemRequest updateAdditionalServiceItemRequest) {
		return additionalServiceItemService.update(updateAdditionalServiceItemRequest);
	}
	

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable int id) {
		return additionalServiceItemService.delete(id); 
	}
	
	

}
