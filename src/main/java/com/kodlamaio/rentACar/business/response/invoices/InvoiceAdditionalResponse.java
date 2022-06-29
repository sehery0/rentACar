package com.kodlamaio.rentACar.business.response.invoices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceAdditionalResponse {
	private int id;
	private String name;
	private double price;
	private String customerName;
	private String customerLastName;


}
