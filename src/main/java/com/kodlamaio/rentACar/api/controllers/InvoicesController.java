package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.request.invoices.UpdateInvoiceRequest;
import com.kodlamaio.rentACar.business.response.invoices.InvoiceIndividualCustomerResponse;
import com.kodlamaio.rentACar.business.response.invoices.InvoiceRentalResponse;
import com.kodlamaio.rentACar.business.response.invoices.InvoiceAdditionalResponse;
import com.kodlamaio.rentACar.business.response.invoices.InvoiceCorporateCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/invoice")
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
	
	@GetMapping("/getRentalInvoice")
	public DataResult<List<InvoiceRentalResponse>> getAllRentalInvoice() {
		return invoiceService.getAllRentalInvoice();
	}
	

	@GetMapping("/getRentalAdditionalServiceInvoice")
	public DataResult<List<InvoiceAdditionalResponse>> getAllAdditionalInvoice() {
		return invoiceService.getAllAdditionalInvoice();
	}
	
	
	@GetMapping("getAllindividualCustomerInvoice")
	public DataResult<List<InvoiceIndividualCustomerResponse>> getAllindividualCustomerInvoice(){
		return this.invoiceService.getAllindividualCustomerInvoice();
	}
	
    @GetMapping("getInvoiceCorporateCustomer")
	public DataResult<List<InvoiceCorporateCustomerResponse>> getAllcorporateCustomerInvoice(){
		return this.invoiceService.getAllcorporateCustomerInvoice();
	}

}
