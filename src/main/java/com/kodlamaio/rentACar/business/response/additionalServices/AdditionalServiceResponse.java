package com.kodlamaio.rentACar.business.response.additionalServices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalServiceResponse {
	private int id;
	private int rentalId;
	private int additionalServiceItemId;

}
