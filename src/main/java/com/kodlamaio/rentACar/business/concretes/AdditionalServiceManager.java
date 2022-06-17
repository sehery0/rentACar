package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalServiceService;
import com.kodlamaio.rentACar.business.request.additionalServices.CreateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.request.additionalServices.UpdateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.response.additionalServices.AdditionalServiceResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalServiceRepository;
import com.kodlamaio.rentACar.entities.concretes.AdditionalService;

@Service
public class AdditionalServiceManager implements AdditionalServiceService{
	
	private AdditionalServiceRepository additionalServiceRepository;
	private ModelMapperService modelMapperService;
	
    @Autowired
	public AdditionalServiceManager(AdditionalServiceRepository additionalServiceRepository, ModelMapperService modelMapperService) {
		super();
		this.additionalServiceRepository = additionalServiceRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);
		this.additionalServiceRepository.save(additionalService);
		return new SuccessDataResult("ADDITIONALSERVICE.ADDED");
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		AdditionalService additionalService = this.modelMapperService.forRequest().map(updateAdditionalServiceRequest, AdditionalService.class);
		this.additionalServiceRepository.save(additionalService);
		return new SuccessDataResult("ADDITIONALSERVICE.UPDATED");
	}

	@Override
	public Result delete(int id) {
		this.additionalServiceRepository.deleteById(id);
		return new SuccessDataResult("ADDITIONALSERVICE.DELETED");
	}

	@Override
	public DataResult<List<AdditionalServiceResponse>> findAllByRentalId(int rentalId) {
		List<AdditionalService> additionalServices = this.additionalServiceRepository.findAll();
	    List<AdditionalServiceResponse> response = additionalServices.stream()
	    		.map(additionalService -> this.modelMapperService.forResponse().map(additionalService, AdditionalServiceResponse.class))
	    		.collect(Collectors.toList());
		return new SuccessDataResult<List<AdditionalServiceResponse>>(response);
	}

}
