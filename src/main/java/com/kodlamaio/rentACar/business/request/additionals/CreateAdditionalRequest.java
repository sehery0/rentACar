package com.kodlamaio.rentACar.business.request.additionals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalRequest {
	private int additionalItemId;
	private int rentalId;

}
