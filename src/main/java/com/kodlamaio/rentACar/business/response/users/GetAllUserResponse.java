package com.kodlamaio.rentACar.business.response.users;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUserResponse {
	private int id;
	private String name;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private LocalDate birthDate;

}
