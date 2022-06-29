package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalService;
import com.kodlamaio.rentACar.business.request.additionals.CreateAdditionalRequest;
import com.kodlamaio.rentACar.business.request.additionals.UpdateAdditionalRequest;
import com.kodlamaio.rentACar.business.response.additionals.GetAllAdditionalResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalRepository;
import com.kodlamaio.rentACar.entities.concretes.Additional;


@Service
public class AdditionalManager implements AdditionalService{
	
    private AdditionalRepository additionalRepository;
	private ModelMapperService modelMapperService;

	public AdditionalManager(AdditionalRepository additionalRepository,
			ModelMapperService modelMapperService) {
		this.additionalRepository = additionalRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<GetAllAdditionalResponse>> findAllByRentalId(int rentalId) {
		List<Additional> additionals = additionalRepository.findAllByRentalId(rentalId);
		List<GetAllAdditionalResponse> GetAlladditionalResponse = additionals.stream()
				.map(additional -> modelMapperService.forResponse().map(additional,
						GetAllAdditionalResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAdditionalResponse>>(GetAlladditionalResponse,"DATA.LISTED.SUCCESSFULLY");
	}

	@Override
	public Result add(CreateAdditionalRequest createAdditionalRequest) {
		Additional additional = modelMapperService.forRequest().map(createAdditionalRequest,
				Additional.class);
		additionalRepository.save(additional);
		return new SuccessResult("ADDITIONAL.SERVICE.ADDED");
	}

	@Override
	public Result update(UpdateAdditionalRequest updateAdditionalRequest) {
		checkIfAdditionalServiceExistById(updateAdditionalRequest.getId());
		Additional additional = modelMapperService.forRequest().map(updateAdditionalRequest,
				Additional.class);
		additionalRepository.save(additional);
		return new SuccessResult("ADDITIONAL.SERVICE.UPDATED");
	}

	@Override
	public Result delete(int id) {
		checkIfAdditionalServiceExistById(id);
		additionalRepository.deleteById(id);
		return new SuccessResult("ADDITIONAL.SERVICE.DELETED");
	}

	private void checkIfAdditionalServiceExistById(int id) {
		Optional<Additional> additional = additionalRepository.findById(id);
		if (additional.get().equals(null))
			throw new BusinessException("ADDITIONAL.SERVICE.IS.NOT.EXIST");
	}


}