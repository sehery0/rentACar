package com.kodlamaio.rentACar.business.request.addresses;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressRequest {
	@NotEmpty
	@NotNull
	private String address;
	private int userId;
	private int addressType;

}
