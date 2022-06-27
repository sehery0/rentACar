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

import com.kodlamaio.rentACar.business.abstracts.AdditionalItemService;
import com.kodlamaio.rentACar.business.request.additionalItems.CreateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.request.additionalItems.UpdateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.response.additionalItems.GetAdditionalItemResponse;
import com.kodlamaio.rentACar.business.response.additionalItems.GetAllAdditionalItemResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/additionalitems")
public class AdditionalItemsController {
	private AdditionalItemService additionalItemService;

	public AdditionalItemsController(AdditionalItemService additionalItemService) {
		this.additionalItemService = additionalItemService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateAdditionalItemRequest creAdditionalItemRequest) {
		return this.additionalItemService.add(creAdditionalItemRequest);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@RequestBody int id) {
		return this.additionalItemService.delete(id);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateAdditionalItemRequest updateAdditionalItemRequest) {
		return this.additionalItemService.update(updateAdditionalItemRequest);
	}
	
	@GetMapping("/getById")
	public DataResult<GetAdditionalItemResponse> getById(@RequestParam int id) {
		return this.additionalItemService.getById(id);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<GetAllAdditionalItemResponse>> getAll(){
		return this.additionalItemService.getAll();
	}
	
}
