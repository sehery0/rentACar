package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalItemService;
import com.kodlamaio.rentACar.business.request.additionalItems.CreateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.request.additionalItems.UpdateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.response.additionalItems.GetAdditionalItemResponse;
import com.kodlamaio.rentACar.business.response.additionalItems.GetAllAdditionalItemResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalItemRepository;
import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;

@Service
public class AdditionalItemManager implements AdditionalItemService{
	
	private AdditionalItemRepository additionalItemRepository;
	private ModelMapperService modelMapperService;
	
	
	
    @Autowired
	public AdditionalItemManager(AdditionalItemRepository additionalItemRepository,
			ModelMapperService modelMapperService) {
		super();
		this.additionalItemRepository = additionalItemRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateAdditionalItemRequest createAdditionalItemRequest) {
		checkIfItemExistsByName(createAdditionalItemRequest.getName());
		AdditionalItem additionalItem = this.modelMapperService.forRequest().map(createAdditionalItemRequest, AdditionalItem.class);
		this.additionalItemRepository.save(additionalItem);
		return new SuccessResult("ADDITIONALITEM.ADDED");
	}

	@Override
	public Result update(UpdateAdditionalItemRequest updateAdditionalItemRequest) {
		checkIfItemExistsByName(updateAdditionalItemRequest.getName());
		AdditionalItem additionalItem = this.modelMapperService.forRequest().map(updateAdditionalItemRequest, AdditionalItem.class);
		this.additionalItemRepository.save(additionalItem);
		return new SuccessResult("ITEM.ADDED");
	}

	@Override
	public Result delete(int id) {
		this.additionalItemRepository.deleteById(id);
		return new SuccessResult("ADDITIONALITEM.DELETED");
	}

	@Override
	public DataResult<GetAdditionalItemResponse> getById(int id) {
			AdditionalItem additionalItem = this.additionalItemRepository.findById(id).get();
			
			GetAdditionalItemResponse response = this.modelMapperService.forResponse().map(additionalItem, GetAdditionalItemResponse.class);
			return new SuccessDataResult<GetAdditionalItemResponse>(response);
	}

	@Override
	public DataResult<List<GetAllAdditionalItemResponse>> getAll() {
		List<AdditionalItem> additionalItems = additionalItemRepository.findAll();
		List<GetAllAdditionalItemResponse> response = additionalItems.stream()
				.map(additionalServiceItem -> this.modelMapperService.forResponse()
						.map(additionalServiceItem, GetAllAdditionalItemResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAdditionalItemResponse>>(response);
	}

	private void checkIfItemExistsByName(String name) {
		AdditionalItem additionalItem = this.additionalItemRepository.findByName(name);
		if (additionalItem != null) {
			throw new BusinessException("ADDITIONALITEM.EXISTS");
		}
	}
	

}
