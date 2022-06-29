package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CorporateCustomerService;
import com.kodlamaio.rentACar.business.request.corporatecustomers.CreateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.request.corporatecustomers.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.response.corporateCustomers.GetAllCorporateCustomerResponse;
import com.kodlamaio.rentACar.business.response.corporateCustomers.GetCorporateCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CorporateCustomerRepository;
import com.kodlamaio.rentACar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService{
	
	ModelMapperService modelMapperService;
	CorporateCustomerRepository corporateCustomerRepository;
	
	

	public CorporateCustomerManager(ModelMapperService modelMapperService,
			CorporateCustomerRepository corporateCustomerRepository) {
		this.modelMapperService = modelMapperService;
		this.corporateCustomerRepository = corporateCustomerRepository;
	}

	@Override
	public DataResult<List<GetAllCorporateCustomerResponse>> getAll() {
		List<CorporateCustomer> corporateCustomers=corporateCustomerRepository.findAll();
		List<GetAllCorporateCustomerResponse> response= corporateCustomers.stream().map(
				corporateCustomer -> modelMapperService.forResponse().map(corporateCustomer, GetAllCorporateCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCorporateCustomerResponse>>(response, "DATA.LISTED.SUCCESSFULLY");
	}

	@Override
	public DataResult<GetCorporateCustomerResponse> findById(int id) {
		checkIfCorporateCustomerExistById(id);
		CorporateCustomer corporateCustomer=corporateCustomerRepository.findById(id).get();
		return new SuccessDataResult<GetCorporateCustomerResponse>(modelMapperService.forResponse().map(corporateCustomer, GetCorporateCustomerResponse.class));

	}

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		checkIfCompanyNameExist(createCorporateCustomerRequest.getCompanyName());
		corporateCustomerRepository.save(modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class));
		return new SuccessDataResult<>("CORPORATE.CUSTOMER.ADDED");
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		checkIfCorporateCustomerExistById(updateCorporateCustomerRequest.getId());
		checkIfCompanyNameExist(updateCorporateCustomerRequest.getCompanyName());
		corporateCustomerRepository.save(modelMapperService.forRequest().map(updateCorporateCustomerRequest, CorporateCustomer.class));
		return new SuccessDataResult<>("CORPORATE.CUSTOMER.UPDATED");
	}

	@Override
	public Result delete(int id) {
		checkIfCorporateCustomerExistById(id);
		corporateCustomerRepository.deleteById(id);
		return new SuccessResult("CORPORATE.CUSTOMER.DELETED");
	}
	
	private void checkIfCorporateCustomerExistById(int id) {
		Optional<CorporateCustomer> corporateCustomer = corporateCustomerRepository.findById(id);
		if (corporateCustomer.get().equals(null))
			throw new BusinessException("CORPORATE.CUSTOMER.IS.NOT.EXIST");

	}
	
	private void checkIfCompanyNameExist(String companyName)
	{
		CorporateCustomer corporateCustomer=corporateCustomerRepository.findByCompanyName(companyName);
		if(corporateCustomer!=null)
			throw new BusinessException("COMPANY.NAME.IS.ALREADY.EXIST");
	}

}
