package com.kodlamaio.rentACar.business.request.additionalServiceItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalServiceItemRequest {
	private String name;
	private double price;

}
