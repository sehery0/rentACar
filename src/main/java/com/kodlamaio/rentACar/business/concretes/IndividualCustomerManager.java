package com.kodlamaio.rentACar.business.concretes;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.IndividualCustomerService;
import com.kodlamaio.rentACar.business.request.individualcustomers.CreateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.request.individualcustomers.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.response.individualCustomers.GetAllIndividualCustomerResponse;
import com.kodlamaio.rentACar.business.response.individualCustomers.GetIndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.PersonCheckService;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.IndividualCustomerRepository;
import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService{
	
	private IndividualCustomerRepository individualCustomerRepository;
	private ModelMapperService modelMapperService;
	private PersonCheckService personCheckService;
	

	public IndividualCustomerManager(IndividualCustomerRepository individualCustomerRepository,
			ModelMapperService modelMapperService, PersonCheckService personCheckService ) {
		this.individualCustomerRepository = individualCustomerRepository;
		this.modelMapperService = modelMapperService;
		this.personCheckService=personCheckService;
		
	}

	@Override
	public DataResult<List<GetAllIndividualCustomerResponse>> getAll() {
		List<IndividualCustomer> individualCustomers= this.individualCustomerRepository.findAll();
		List<GetAllIndividualCustomerResponse> response=individualCustomers.stream().map(
				individualCustomer -> modelMapperService.forResponse().map(individualCustomer, GetAllIndividualCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllIndividualCustomerResponse>>(response, "DATA.LISTED.SUCCESSFULLY");
	}

	@Override
	public DataResult<GetIndividualCustomerResponse> findById(int id) {
		checkIfIndividualCustomerExistById(id);
		IndividualCustomer individualCustomer=individualCustomerRepository.findById(id).get();
		return new SuccessDataResult<GetIndividualCustomerResponse>(modelMapperService.forResponse().map(individualCustomer, GetIndividualCustomerResponse.class));
	}

	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		checkIfEmailExist(createIndividualCustomerRequest.getEmail());
		checkIfIdentityNumberExist(createIndividualCustomerRequest.getIdentityNumber());
		IndividualCustomer individualCustomer=modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
		checkUserExistsByNationality(individualCustomer);
		individualCustomerRepository.save(individualCustomer);
		return new SuccessResult("INDIVIDUAL.CUSTOMER.ADDED");
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		checkIfIndividualCustomerExistById(updateIndividualCustomerRequest.getId());
		checkIfEmailExist(updateIndividualCustomerRequest.getEmail());
		checkIfIdentityNumberExist(updateIndividualCustomerRequest.getIdentityNumber());
		individualCustomerRepository.save(modelMapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class));
		return null;
	}

	@Override
	public Result delete(int id) {
		checkIfIndividualCustomerExistById(id);
		individualCustomerRepository.deleteById(id);
		return new SuccessResult("INDIVIDUAL.CUSTOMER.DELETED");
	}
	
	private void checkIfEmailExist(String email)
	{
		IndividualCustomer individualCustomer=individualCustomerRepository.findByEmail(email);
		if(individualCustomer!=null)
			throw new BusinessException("EMAIL.IS.ALREADY.EXIST");
			
	}
	
	private void checkIfIdentityNumberExist(String identityNumber)
	{
		IndividualCustomer individualCustomer=individualCustomerRepository.findByIdentityNumber(identityNumber);
		if(individualCustomer!=null)
			throw new BusinessException("IDENTITY.NUMBER.IS.ALREADY.EXIST");
			
	}
	
	private void checkIfIndividualCustomerExistById(int id)
	{
		Optional<IndividualCustomer> individualCustomer=individualCustomerRepository.findById(id);
		if(individualCustomer.get().equals(null))
			throw new BusinessException("INDIVIDUAL.CUSTOMER.IS.NOT.EXIST");
			
	}
	
	private void checkUserExistsByNationality(IndividualCustomer individualCustomer) {

		if (!personCheckService.checkIfUserExist(individualCustomer)) {

			throw new BusinessException("USER.IS.NOT.VALID");
		}

	}

	@Override
	public String getIdentityNumber(int customerId) {
		return individualCustomerRepository.findById(customerId).get().getIdentityNumber();
	}
	

}
