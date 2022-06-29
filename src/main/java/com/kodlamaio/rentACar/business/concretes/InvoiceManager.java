package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.request.invoices.UpdateInvoiceRequest;
import com.kodlamaio.rentACar.business.response.invoices.InvoiceIndividualCustomerResponse;
import com.kodlamaio.rentACar.business.response.invoices.InvoiceRentalResponse;
import com.kodlamaio.rentACar.business.response.invoices.InvoiceAdditionalResponse;
import com.kodlamaio.rentACar.business.response.invoices.InvoiceCorporateCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalItemRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.InvoiceRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.Additional;
import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;
import com.kodlamaio.rentACar.entities.concretes.Invoice;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@Service
public class InvoiceManager implements InvoiceService{
	
	private InvoiceRepository invoiceRepository;
	private ModelMapperService modelMapperService;
	

	@Autowired
	public InvoiceManager(InvoiceRepository invoiceRepository, ModelMapperService modelMapperService) {
		this.invoiceRepository = invoiceRepository;
		this.modelMapperService = modelMapperService;
	}
	
	
	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		checkIfInvoiceExistByRentalId(createInvoiceRequest.getRentalId());
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		this.invoiceRepository.save(invoice);
		return new SuccessResult("INVOICE.ADDED");
	}


	@Override
	public Result delete(int id) {
		checkIfInvoiceExistById(id);
		this.invoiceRepository.deleteById(id);
		return new SuccessResult("INVOICE.DELETED");
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		checkIfInvoiceExistByRentalId(updateInvoiceRequest.getRentalId());
		Invoice invoice = this.modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
		this.invoiceRepository.save(invoice);
		return new SuccessResult("INVOICE.UPDATED");
	}

	@Override
	public DataResult<List<InvoiceCorporateCustomerResponse>> getAllcorporateCustomerInvoice() {
		List<Invoice> invoiceCorporateCustomers=invoiceRepository.findAll();
		List<InvoiceCorporateCustomerResponse> response=invoiceCorporateCustomers.stream().map(
				invoiceCorporateCustomer -> modelMapperService.forResponse().map(invoiceCorporateCustomer, InvoiceCorporateCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<InvoiceCorporateCustomerResponse>>(response,"CORPORATE.CUSTOMER.LISTED.SUCCESSFULLY");
	}



	@Override
	public DataResult<List<InvoiceIndividualCustomerResponse>> getAllindividualCustomerInvoice() {
		List<Invoice> invoices = this.invoiceRepository.findAll();
		List<InvoiceIndividualCustomerResponse> response = invoices.stream()
				.map(invoice -> this.modelMapperService.forResponse()
				.map(invoice, InvoiceIndividualCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<InvoiceIndividualCustomerResponse>>(response);
	}



	@Override
	public DataResult<List<InvoiceRentalResponse>> getAllRentalInvoice() {
		List<Invoice> invoices = invoiceRepository.findAll();
		List<InvoiceRentalResponse> response = invoices.stream()
				.map(invoice -> modelMapperService.forResponse().map(invoice, InvoiceRentalResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<>(response, "DATA.LISTED.SUCCESSFULLLY");
	}



	@Override
	public DataResult<List<InvoiceAdditionalResponse>> getAllAdditionalInvoice() {
		List<Invoice> invoices = invoiceRepository.findAll();
		List<InvoiceAdditionalResponse> response = invoices.stream()
				.map(invoice -> modelMapperService.forResponse().map(invoice, InvoiceAdditionalResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<>(response, "DATA.LISTED.SUCCESSFULLLY");
	}
	
	
	private void checkIfInvoiceExistById(int id) {
		Optional<Invoice> invoice = invoiceRepository.findById(id);
		if (invoice.get().equals(null))
			throw new BusinessException("INVOICE.IS.NOT.EXIST");
	}

	private void checkIfInvoiceExistByRentalId(int rentalId) {
		List<AdditionalItem> additionalItem = invoiceRepository.findByRentalId(rentalId);
		if (additionalItem != null)
			throw new BusinessException("INVOICE.IS.ALREADY.EXIST");
	}

}
