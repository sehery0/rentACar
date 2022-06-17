package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.ColorService;
import com.kodlamaio.rentACar.business.request.colors.CreateColorRequest;
import com.kodlamaio.rentACar.business.request.colors.DeleteColorRequest;
import com.kodlamaio.rentACar.business.request.colors.UpdateColorRequest;
import com.kodlamaio.rentACar.business.response.colors.GetAllColorResponse;
import com.kodlamaio.rentACar.business.response.colors.GetColorResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.ColorRepository;
import com.kodlamaio.rentACar.entities.concretes.Color;


@Service
public class ColorManager implements ColorService {

	@Autowired
	private ColorRepository colorRepository;
	@Autowired
	private ModelMapperService modelMapperService;

	@Override
	public Result add(CreateColorRequest createColorRequest) {
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorRepository.save(color);
		return new SuccessResult("COLOR.ADDED");

	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorRepository.save(color);
		
		return new SuccessResult("COLOR.UPDATED");
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		Color color = this.modelMapperService.forRequest().map(deleteColorRequest, Color.class);
		this.colorRepository.delete(color);
		return new SuccessResult("COLOR.DELETED") ;
	}

	@Override
	public DataResult<GetColorResponse> getById(int id) {
		Color color = this.colorRepository.findById(id);
		GetColorResponse response  = this.modelMapperService.forResponse().map(color, GetColorResponse.class);
		return new SuccessDataResult<GetColorResponse>(response);
	}

	@Override
	public DataResult<List<GetAllColorResponse>> getAll() {
		List<Color> colors = this.colorRepository.findAll();
		
		List<GetAllColorResponse> response = colors.stream()
				.map(color -> modelMapperService.forResponse().map(color, GetAllColorResponse.class))
				.collect(Collectors.toList());;
		
		return new SuccessDataResult<List<GetAllColorResponse>>(response);
	}

	

}
