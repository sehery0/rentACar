package com.kodlamaio.rentACar.core.utilities.results.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Service;

@Service //Otomatik dependency enjection yapar.
public class ModelMapperServiceImpl implements ModelMapperService{
	
	private ModelMapper modelMapper;
	
	public ModelMapperServiceImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public ModelMapper forResponse() {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
		return this.modelMapper;
	}

	//bean bellekte oluşturulmuş enctense
	@Override
	public ModelMapper forRequest() {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);

		return this.modelMapper;
	}

}
