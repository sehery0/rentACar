package com.kodlamaio.rentACar.api.controllers;

import java.rmi.RemoteException;
import java.util.List;



import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.UserService;
import com.kodlamaio.rentACar.business.request.users.CreateUserRequest;
import com.kodlamaio.rentACar.business.request.users.UpdateUserRequest;
import com.kodlamaio.rentACar.business.response.users.GetAllUserResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.entities.concretes.User;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	
	private UserService userService;

	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}


	@PostMapping("/id")
	public Result delete(@RequestBody int id) {
		return this.userService.delete(id);
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateUserRequest createUserRequest) throws NumberFormatException, RemoteException {
		return this.userService.add(createUserRequest);
	}

	@PostMapping("/{id}")
	public Result update(@RequestBody UpdateUserRequest updateUserRequest) {
		return this.userService.update(updateUserRequest);
	}

	@GetMapping("/page/{pageNo}")
	public DataResult<Page<User>> findPaginated(@PathVariable(value = "pageNo") int pageNo) {
		int pageSize = 5;
		Page<User> page = userService.findPaginated(pageNo, pageSize);
		return new SuccessDataResult<Page<User>>(page);
		
		

	}

}
