package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.request.invoices.UpdateInvoiceRequest;
import com.kodlamaio.rentACar.business.response.invoices.GetAllInvoiceResponse;
import com.kodlamaio.rentACar.business.response.invoices.GetInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;

public class InvoicesController {
	
	private InvoiceService invoiceService;
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateInvoiceRequest createInvoiceRequest) {
		return this.invoiceService.add(createInvoiceRequest);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@RequestBody int id) {
		return this.invoiceService.delete(id);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateInvoiceRequest updateInvoiceRequest) {
		return this.invoiceService.update(updateInvoiceRequest);
	}
	
	@GetMapping("/getById")
	public DataResult<GetInvoiceResponse> getById(int id){
		return this.invoiceService.getById(id);
	}
	
	@GetMapping("getAll")
	public DataResult<List<GetAllInvoiceResponse>> getAll(){
		return this.invoiceService.getAll();
	}
	
	@GetMapping("/getAllAdditionalItems")
	public DataResult<List<AdditionalItem>> getAllAdditionalItems(@RequestParam int rentalId) {
		return this.invoiceService.getAllAdditionalItems(rentalId);
	}

}
