package com.kodlamaio.rentACar.business.request.cars;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Empty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	
	@NotBlank
	@Size(min=2, max=50)
	private String description;
	@NotEmpty
	@Min(10)
	private double dailyPrice;
	private int brandId;
	private int colorId;
	

}
