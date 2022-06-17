package com.kodlamaio.rentACar.business.concretes;


import java.rmi.RemoteException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.UserService;
import com.kodlamaio.rentACar.business.request.users.CreateUserRequest;
import com.kodlamaio.rentACar.business.request.users.UpdateUserRequest;
import com.kodlamaio.rentACar.business.response.users.GetAllUserResponse;
import com.kodlamaio.rentACar.business.response.users.GetUserResponse;
import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.PersonCheckService;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.UserRepository;
import com.kodlamaio.rentACar.entities.concretes.User;

@Service
public class UserManager implements UserService {
	
	private UserRepository userRepository;
	private ModelMapperService modelMapperService;
	private PersonCheckService personCheckService;
	
	@Autowired
	public UserManager(UserRepository userRepository, ModelMapperService modelMapperService, PersonCheckService personCheckService) {
		super();
		this.userRepository = userRepository;
		this.modelMapperService = modelMapperService;
		this.personCheckService = personCheckService;
	}

	@Override
	public Result add(CreateUserRequest createUserRequest) throws NumberFormatException, RemoteException {
		User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
		checkUserExistByNationality(user);
		this.userRepository.save(user);
		return new SuccessResult("USER.ADDED");
	}

	@Override
	public Result delete(int id) {
		this.userRepository.deleteById(id);
		return new SuccessResult("USER.DELETED");
	}
	
	
	@Override
	public Result update(UpdateUserRequest updateUserRequest) {
		User user = this.modelMapperService.forRequest().map(updateUserRequest, User.class);
		this.userRepository.save(user);
		return new SuccessResult("USER.UPDATED");
	}


	@Override
	public Page<User> findPaginated(int pageNo, int pageSize) {
		//the page count starts at zero
		Pageable pageable=PageRequest.of(pageNo-1,pageSize);
		return userRepository.findAll(pageable);
	}

	private void checkUserExistByNationality(User user) throws NumberFormatException, RemoteException {
		if(!personCheckService.checkPerson(user)) {
			throw new BusinessException("USER.WASN'T.ADDED");
		}
	}
	

	
}
