package com.kodlamaio.rentACar.business.response.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCarResponse {
	private int id;
	private String description;
	private double dailyPrice;
	private int brandId;
	private int colorId;
	private int carScore;

}
