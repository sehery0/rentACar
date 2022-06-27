package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.kodlamaio.rentACar.business.abstracts.AddressService;
import com.kodlamaio.rentACar.business.request.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.business.request.addresses.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.response.addresses.GetAddressResponse;
import com.kodlamaio.rentACar.business.response.addresses.GetAllAddressResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AddressRepository;
import com.kodlamaio.rentACar.entities.concretes.Address;

public class AddressManager implements AddressService{
	
	private AddressRepository addressRepository;
	private ModelMapperService modelMapperService;

	@Override
	public Result add(CreateAddressRequest createAddressRequest) {
		Address address = this.modelMapperService.forRequest().map(createAddressRequest, Address.class);
		this.addressRepository.save(address);
		return new SuccessResult("ADDRESS.ADDED");
	}

	@Override
	public Result delete(int id) {
		this.addressRepository.deleteById(id);
		return new SuccessResult("ADDRESS.DELETED");
	}

	@Override
	public Result update(UpdateAddressRequest updateAddressRequest) {
		Address address = this.modelMapperService.forRequest().map(updateAddressRequest, Address.class);
		this.addressRepository.save(address);
		return new SuccessResult("ADDRESS.UPDATED");
	}

	@Override
	public DataResult<List<GetAllAddressResponse>> getAll() {
		List<Address> getAllAddressesResponses = this.addressRepository.findAll();
		
		List<GetAllAddressResponse> response = getAllAddressesResponses.stream()
				.map(address -> this.modelMapperService.forResponse().map(address, GetAllAddressResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAddressResponse>>(response);
	}

	@Override
	public DataResult<GetAddressResponse> getById(int id) {
		Address address = this.addressRepository.findById(id).get();
		
		GetAddressResponse response = this.modelMapperService.forResponse().map(address, GetAddressResponse.class);
		
		return new SuccessDataResult<GetAddressResponse>(response);
	}

	@Override
	public DataResult<List<GetAllAddressResponse>> getAllBillAddress(int userId, int addressType) {
		List<Address> addresses = this.addressRepository.getByUserIdAndAddressType(userId, addressType);
		List<GetAllAddressResponse> response = addresses.stream()
				.map(address -> this.modelMapperService.forResponse()
				.map(address, GetAllAddressResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAddressResponse>>(response);
	}

	@Override
	public DataResult<List<GetAllAddressResponse>> getAllContactAddress(int userId, int addressType) {
		List<Address> addresses = this.addressRepository.getByUserIdAndAddressType(userId, addressType);
		List<GetAllAddressResponse> response = addresses.stream()
				.map(address -> this.modelMapperService.forResponse()
				.map(address, GetAllAddressResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAddressResponse>>(response);
	}
}
