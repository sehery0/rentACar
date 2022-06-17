package com.kodlamaio.rentACar.business.request.rentals;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
	private int id;
	private LocalDate pickUpDate;
	private LocalDate returnedDate;
	private int carId;
	private int pickUpCityId;
	private int returnedCityId;
	private int userId;

}
