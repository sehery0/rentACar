package com.kodlamaio.rentACar.business.response.additionalItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAdditionalItemResponse {
	private int id;
	private String name;
	private double price;

}
