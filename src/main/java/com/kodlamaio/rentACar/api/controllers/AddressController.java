package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodlamaio.rentACar.business.abstracts.AddressService;
import com.kodlamaio.rentACar.business.request.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.business.request.addresses.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.response.addresses.GetAddressResponse;
import com.kodlamaio.rentACar.business.response.addresses.GetAllAddressResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public class AddressController {

	private AddressService addressService;
	
	
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateAddressRequest createAddressRequest) {
		return this.addressService.add(createAddressRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateAddressRequest updateAddressRequest) {
		return this.addressService.update(updateAddressRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody int id) {
		return this.addressService.delete(id);
	}
	
	@GetMapping("getById")
	public DataResult<GetAddressResponse> getById(@RequestParam int id) {
		return this.addressService.getById(id);
	}
	
	@GetMapping("getAll")
	public DataResult<List<GetAllAddressResponse>> getAll(){
		return this.addressService.getAll();
	}
	
	@GetMapping("getAllBillAddress")
	public DataResult<List<GetAllAddressResponse>> getAllBillAddress(@RequestParam int userId, int addressType){
		return this.addressService.getAllBillAddress(userId, addressType);
	}
	
	@GetMapping("getAllContactAddress")
	public DataResult<List<GetAllAddressResponse>> getAllContactAddress(@RequestParam int userId, int addressType){
		return this.addressService.getAllContactAddress(userId, addressType);
	}

}
