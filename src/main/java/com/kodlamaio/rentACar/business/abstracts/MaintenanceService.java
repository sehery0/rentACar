package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.maintenances.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.request.maintenances.DeleteMaintenanceRequest;
import com.kodlamaio.rentACar.business.request.maintenances.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.response.maintenances.GetAllMaintenanceResponse;
import com.kodlamaio.rentACar.business.response.maintenances.GetMaintenanceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Maintenance;

public interface MaintenanceService {
	Result add(CreateMaintenanceRequest createMaintenanceRequest);

	Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest);

	Result update(UpdateMaintenanceRequest updateMaintenanceRequest);

	DataResult<GetMaintenanceResponse> getById(int id);

	DataResult<List<GetAllMaintenanceResponse>> getAll();

}
