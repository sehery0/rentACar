package com.kodlamaio.rentACar.core.utilities.results.mapping;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
	ModelMapper forResponse();
	ModelMapper forRequest();
	

}
