package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.business.request.addresses.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.response.addresses.GetAddressResponse;
import com.kodlamaio.rentACar.business.response.addresses.GetAllAddressResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AddressService {
	Result add(CreateAddressRequest createAddressRequest);
	Result delete(int id);
	Result update(UpdateAddressRequest updateAddressRequest);
	DataResult<List<GetAllAddressResponse>> getAll();
	DataResult<GetAddressResponse> getById(int id);
	DataResult<List<GetAllAddressResponse>> getAllBillAddress(int userId, int addressType);
	DataResult<List<GetAllAddressResponse>> getAllContactAddress(int userId, int addressType);

}
