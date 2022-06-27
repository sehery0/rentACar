package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.request.invoices.UpdateInvoiceRequest;
import com.kodlamaio.rentACar.business.response.invoices.GetAllInvoiceResponse;
import com.kodlamaio.rentACar.business.response.invoices.GetInvoiceResponse;
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
	private AdditionalRepository additionalRepository;
	private RentalRepository rentalRepository;
	private AdditionalItemRepository additionalItemRepository;
	

	@Autowired
	public InvoiceManager(InvoiceRepository invoiceRepository, ModelMapperService modelMapperService,
			AdditionalRepository additionalRepository, RentalRepository rentalRepository,
			AdditionalItemRepository additionalItemRepository) {
		super();
		this.invoiceRepository = invoiceRepository;
		this.modelMapperService = modelMapperService;
		this.additionalRepository = additionalRepository;
		this.rentalRepository = rentalRepository;
		this.additionalItemRepository = additionalItemRepository;
	}

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		invoice.setCurrentDate(LocalDate.now());
		invoice.setTotalPrice(calculateTotalPrice(createInvoiceRequest.getRentalId()));
		
		this.invoiceRepository.save(invoice);
		
		return new SuccessResult("INVOICE.ADDED");
	}


	@Override
	public Result delete(int id) {
		this.invoiceRepository.deleteById(id);
		return new SuccessResult("INVOICE.DELETED");
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		Invoice invoice = this.modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
		this.invoiceRepository.save(invoice);
		return new SuccessResult("INVOICE.UPDATED");
	}

	@Override
	public DataResult<GetInvoiceResponse> getById(int id) {
		Invoice invoice = this.invoiceRepository.findById(id).get();
		GetInvoiceResponse response = this.modelMapperService.forResponse().map(invoice, GetInvoiceResponse.class);
		return new SuccessDataResult<GetInvoiceResponse>(response);
	}

	@Override
	public DataResult<List<GetAllInvoiceResponse>> getAll() {
		List<Invoice> invoices = this.invoiceRepository.findAll();
		List<GetAllInvoiceResponse> response = invoices.stream()
				.map(invoice -> this.modelMapperService.forResponse()
				.map(invoice, GetAllInvoiceResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllInvoiceResponse>>(response);
	
	}

	@Override
	public DataResult<List<AdditionalItem>> getAllAdditionalItems(int rentalId) {
		List<Additional> additionals = this.additionalRepository.findByRentalId(rentalId);
		List<AdditionalItem> additionalItems = new ArrayList<AdditionalItem>();
		
		for (Additional additional : additionals) {
			AdditionalItem additionalItem = this.additionalItemRepository.findById(additional.getAdditionalItem().getId()).get();
			additionalItems.add(additionalItem);
		}
		return new SuccessDataResult<List<AdditionalItem>>(additionalItems);
	}

	
	private double calculateTotalPrice(int rentalId) {
		Rental rental = this.rentalRepository.getById(rentalId);
		double totalPrice = rental.getTotalPrice() + allRentalAdditionalTotalPrice(rentalId);
		return totalPrice;
	}
	
	private double allRentalAdditionalTotalPrice(int id) {
		double totalAdditional = 0;
		List<Additional> additional = this.additionalRepository.findByRentalId(id);
		for (Additional additionals : additional) {
			totalAdditional += additionals.getTotalPrice();
		}
		return totalAdditional;
	}
}
