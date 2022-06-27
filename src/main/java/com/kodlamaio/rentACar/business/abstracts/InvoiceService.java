package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.request.invoices.UpdateInvoiceRequest;
import com.kodlamaio.rentACar.business.response.invoices.GetAllInvoiceResponse;
import com.kodlamaio.rentACar.business.response.invoices.GetInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;

public interface InvoiceService {
	Result add(CreateInvoiceRequest createInvoiceRequest);
	Result delete(int id);
	Result update(UpdateInvoiceRequest updateInvoiceRequest);
	DataResult<GetInvoiceResponse> getById(int id);
	DataResult<List<GetAllInvoiceResponse>> getAll();
	DataResult<List<AdditionalItem>> getAllAdditionalItems(int rentalId);


}
