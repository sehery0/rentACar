package com.kodlamaio.rentACar.business.response.rentals;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllRentalResponse {
	private int id;
	private Date pickUpdate;
	private Date returnDate;
	private double totalDays;
	private int carId;
	private int pickUpCityId;
	private int returnCityId;
	private int userId;

}
