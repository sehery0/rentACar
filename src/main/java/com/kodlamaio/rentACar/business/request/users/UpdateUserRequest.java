package com.kodlamaio.rentACar.business.request.users;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
	private int id;
	private String name;
	private String lastName;
	private String identityNumber;
	private String email;
	private String password;
	private LocalDate birthDate;

}
