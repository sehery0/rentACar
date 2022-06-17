package com.kodlamaio.rentACar.business.response.maintenances;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllMaintenanceResponse {
	private int id;
	private LocalDate dateSent;
	private LocalDate dateReturned;
	private int carId;
}
