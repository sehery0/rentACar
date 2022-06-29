package com.kodlamaio.rentACar.business.response.individualCustomers;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetIndividualCustomerResponse {
	private int individualCustomerId;
	private String firstName;
	private String lastName;
	private String nationality;
	private String email;
	private String password;

}
