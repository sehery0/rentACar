package com.kodlamaio.rentACar.business.response.cars;

import com.kodlamaio.rentACar.entities.concretes.Car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCarResponse {
	private int id;
	private String description;
	private String licensePlate;
	private double dailyPrice;
	private int kilometer;
	private int state;
	private int brand_id;
	private int color_id;
	private int carScore;
	
	

}
