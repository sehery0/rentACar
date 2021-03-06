package com.kodlamaio.rentACar.business.request.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {

	private int id;
	private String description;
	private double dailyPrice;
	private String licensePlate;
	private int kilometer;
	private int brand_id;
	private int color_id;
	private int minFindexScore;;
}
