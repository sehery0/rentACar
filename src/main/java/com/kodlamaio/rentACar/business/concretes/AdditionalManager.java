package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalService;
import com.kodlamaio.rentACar.business.request.additionals.CreateAdditionalRequest;
import com.kodlamaio.rentACar.business.request.additionals.UpdateAdditionalRequest;
import com.kodlamaio.rentACar.business.response.additionals.GetAdditionalResponse;
import com.kodlamaio.rentACar.business.response.additionals.GetAllAdditionalResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalItemRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.Additional;
import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@Service
public class AdditionalManager implements AdditionalService{
	
	private AdditionalRepository additionalRepository;
	private AdditionalItemRepository additionalItemRepository;
	private ModelMapperService modelMapperService;
	private RentalRepository rentalRepository;

	
    @Autowired
    public AdditionalManager(AdditionalRepository additionalRepository,
			AdditionalItemRepository additionalItemRepository, ModelMapperService modelMapperService,
			RentalRepository rentalRepository) {
		super();
		this.additionalRepository = additionalRepository;
		this.additionalItemRepository = additionalItemRepository;
		this.modelMapperService = modelMapperService;
		this.rentalRepository = rentalRepository;
	}

	@Override
	public Result add(CreateAdditionalRequest createAdditionalRequest) {
        Additional additional = this.modelMapperService.forRequest().map(createAdditionalRequest, Additional.class);
		//
		int rentalTotalDays = this.rentalRepository.findById(createAdditionalRequest.getRentalId()).getTotalDays();
		additional.setTotalDays(rentalTotalDays);
		
		double additionalItemPrice = this.additionalItemRepository.findById(createAdditionalRequest.getAdditionalItemId()).get().getPrice();
		double totalPrice = calculateTotalPriceAdditional(rentalTotalDays, additionalItemPrice);
		additional.setTotalPrice(totalPrice);
		
		this.additionalRepository.save(additional);
		return new SuccessResult("ADDITIONAL.ADDED");
	}

	

	@Override
	public Result update(UpdateAdditionalRequest updateAdditionalRequest) {
		Additional additional = this.modelMapperService.forRequest()
				.map(updateAdditionalRequest, Additional.class);
		
		Rental rental = this.rentalRepository.findById(updateAdditionalRequest.getRentalId());
		int rentalTotalDays = rental.getTotalDays();
		additional.setTotalDays(rentalTotalDays);
		
		double additionalItemPrice = this.additionalItemRepository.findById(updateAdditionalRequest.getAdditionalItemId()).get().getPrice();
		double totalPrice = calculateTotalPriceAdditional(rentalTotalDays, additionalItemPrice);
		additional.setTotalPrice(totalPrice);
		
		this.additionalRepository.save(additional);		
		return new SuccessResult("ADDITIONALSERVICE.UPDATED");
	}

	@Override
	public Result delete(int id) {
		this.additionalRepository.deleteById(id);
		return new SuccessResult("ADDITIONAL.DELETED");
	}

	@Override
	public DataResult<GetAdditionalResponse> getById(int id) {
        Additional additional = this.additionalRepository.findById(id).get();
		
		GetAdditionalResponse response = this.modelMapperService.forResponse()
				.map(additional, GetAdditionalResponse.class);
		return new SuccessDataResult<GetAdditionalResponse>(response, "GET.ADDITIONALSERVICE");   
	}
	
	@Override
	public DataResult<List<GetAllAdditionalResponse>> getAll() {
		List<Additional> getAllAdditional = this.additionalRepository.findAll();
		
		List<GetAllAdditionalResponse> response = getAllAdditional.stream()
				.map(additionalService->this.modelMapperService.forResponse()
						.map(getAllAdditional, GetAllAdditionalResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAdditionalResponse>>(response,"GET.ALL.ADDITIONALSERVICE");
	}
	
	private double calculateTotalPriceAdditional(int days, double price) {
		return days*price;
	}	

}


