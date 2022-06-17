package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.MaintenanceService;
import com.kodlamaio.rentACar.business.request.colors.UpdateColorRequest;
import com.kodlamaio.rentACar.business.request.maintenances.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.request.maintenances.DeleteMaintenanceRequest;
import com.kodlamaio.rentACar.business.request.maintenances.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.response.maintenances.GetAllMaintenanceResponse;
import com.kodlamaio.rentACar.business.response.maintenances.GetMaintenanceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Maintenance;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenancesController {
	@Autowired
	MaintenanceService maintenanceService;

	@PostMapping("/add")
	public Result add(@RequestBody CreateMaintenanceRequest createMaintenanceRequest) {
		return this.maintenanceService.add(createMaintenanceRequest);

	}

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteMaintenanceRequest deleteMaintenanceRequest) {
		return this.maintenanceService.delete(deleteMaintenanceRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateMaintenanceRequest updateMaintenanceRequest) {
		return this.maintenanceService.update(updateMaintenanceRequest);
	}

	@GetMapping("/getById")
	public DataResult<GetMaintenanceResponse> getById(@RequestParam int id) {
		return this.maintenanceService.getById(id);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetAllMaintenanceResponse>> getAll() {
		return this.maintenanceService.getAll();
	}

}
