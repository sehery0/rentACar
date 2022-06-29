package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.request.invoices.UpdateInvoiceRequest;
import com.kodlamaio.rentACar.business.response.invoices.InvoiceIndividualCustomerResponse;
import com.kodlamaio.rentACar.business.response.invoices.InvoiceRentalResponse;
import com.kodlamaio.rentACar.business.response.invoices.InvoiceAdditionalResponse;
import com.kodlamaio.rentACar.business.response.invoices.InvoiceCorporateCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface InvoiceService {
	Result add(CreateInvoiceRequest createInvoiceRequest);
	Result delete(int id);
	Result update(UpdateInvoiceRequest updateInvoiceRequest);
	DataResult<List<InvoiceCorporateCustomerResponse>> getAllcorporateCustomerInvoice();
	DataResult<List<InvoiceIndividualCustomerResponse>> getAllindividualCustomerInvoice();
	DataResult<List<InvoiceRentalResponse>> getAllRentalInvoice();
	DataResult<List<InvoiceAdditionalResponse>> getAllAdditionalInvoice();


}
