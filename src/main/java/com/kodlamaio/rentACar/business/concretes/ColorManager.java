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
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.ColorRepository;
import com.kodlamaio.rentACar.entities.concretes.Color;


@Service
public class ColorManager implements ColorService {

	
	private ColorRepository colorRepository;
	private ModelMapperService modelMapperService;
	
	
	@Autowired
	public ColorManager(ColorRepository colorRepository, ModelMapperService modelMapperService) {
		super();
		this.colorRepository = colorRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {
		checkIfColorExistsByName(createColorRequest.getName());
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
	public Result delete(int id) {
		this.colorRepository.deleteById(id);
		return new SuccessResult("COLOR.DELETED") ;
	}

	@Override
	public DataResult<GetColorResponse> findById(int id) {
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

	private void checkIfColorExistsByName(String name) {
		Color currentColor = this.colorRepository.findByName(name);
		if (currentColor != null) 
			throw new BusinessException("COLOR.EXISTS");
		}
	
}
