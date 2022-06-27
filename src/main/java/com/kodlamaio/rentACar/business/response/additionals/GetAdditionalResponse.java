package com.kodlamaio.rentACar.business.response.additionals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAdditionalResponse {
	private int totalDays;
	private double totalPrice;
	private int additionalItemId;
	private int rentalId;

}
