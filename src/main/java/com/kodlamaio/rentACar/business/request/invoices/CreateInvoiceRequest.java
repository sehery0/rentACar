package com.kodlamaio.rentACar.business.request.invoices;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {
	private LocalDate invoicedDate;
	private int rentalId;
}
