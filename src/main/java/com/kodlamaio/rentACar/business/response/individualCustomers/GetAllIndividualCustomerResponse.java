package com.kodlamaio.rentACar.business.response.individualCustomers;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllIndividualCustomerResponse {
	private String firstName;
	private String lastName;
	private String nationality;
	private LocalDate birthDate;
	private String email;
	private String password;

}
