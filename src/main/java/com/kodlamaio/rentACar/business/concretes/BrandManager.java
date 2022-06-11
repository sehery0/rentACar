package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.BrandService;
import com.kodlamaio.rentACar.business.request.brands.CreateBrandRequest;
import com.kodlamaio.rentACar.business.request.brands.DeleteBrandRequest;
import com.kodlamaio.rentACar.business.request.brands.UpdateBrandRequest;
import com.kodlamaio.rentACar.business.response.brands.GetAllBrandResponse;
import com.kodlamaio.rentACar.business.response.brands.GetBrandResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.core.utilities.results.mapping.ModelMapperService;
import com.kodlamaio.rentACar.dataAccess.abstracts.BrandRepository;
import com.kodlamaio.rentACar.entities.concretes.Brand;

//BrandServiceImpl
@Service
public class BrandManager implements BrandService {

	
	@Autowired
	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;
	
	

	public BrandManager(BrandRepository brandRepository, ModelMapperService modelMapperService) {
		this.brandRepository = brandRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		// mapping
		checkIfBrandExistByName(createBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND.ADDED");

	}
	
	private void checkIfBrandExistByName(String name) {
	Brand currentBrand = this.brandRepository.findByName(name);
	if (currentBrand!=null) 
		throw new BusinessException("BRAND.EXIST");
	
	}
	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND.DELETED");

	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		Brand brand = this.modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
		this.brandRepository.delete(brand);
		return new SuccessResult("BRAND.UPDATED");

	}

	@Override
	public DataResult<GetBrandResponse> getById(int id) {
		Brand brand = this.brandRepository.findById(id);
		
		GetBrandResponse response = this.modelMapperService.forResponse().map(brand, GetBrandResponse.class);
		return new SuccessDataResult<GetBrandResponse>(response);
		}

	@Override
	public DataResult<List<GetAllBrandResponse>> getAll() {
		List<Brand> brands = this.brandRepository.findAll();
		
		List<GetAllBrandResponse> response = brands.stream()
				.map(brand -> this.modelMapperService.forResponse()
						.map(brand, GetAllBrandResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllBrandResponse>>(response);
	}
	 

}
