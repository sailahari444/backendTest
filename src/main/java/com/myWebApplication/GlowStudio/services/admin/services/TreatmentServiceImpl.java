package com.myWebApplication.GlowStudio.services.admin.services;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myWebApplication.GlowStudio.dto.ServiceResponseDto;
import com.myWebApplication.GlowStudio.dto.TreatmentDto;
import com.myWebApplication.GlowStudio.entity.Services;
import com.myWebApplication.GlowStudio.repository.TreatmentRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TreatmentServiceImpl implements TreatmentService{

	private final TreatmentRepo treatmentRepo;

	public TreatmentServiceImpl(TreatmentRepo treatmentRepo) {
		super();
		this.treatmentRepo = treatmentRepo;
	}

	
	public boolean createService(TreatmentDto treatmentDto) {
		try {
			Services services = new Services();
			services.setServiceName(treatmentDto.getServiceName());
			services.setPrice(treatmentDto.getPrice());
			services.setType(treatmentDto.getType());
			services.setAvailability(true);
		
			treatmentRepo.save(services);
			return true;
			
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public ServiceResponseDto getAllServices(int pageNo) {
		Pageable pageable = PageRequest.of(pageNo, 6);
		Page<Services> servicePage = treatmentRepo.findAll(pageable);
		
		ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
		serviceResponseDto.setPageNo(servicePage.getPageable().getPageNumber());
		serviceResponseDto.setTotalPages(servicePage.getTotalPages());
		serviceResponseDto.setServiceDtoList(servicePage.stream().map(Services::getServiceDto).collect(Collectors.toList()));
		return serviceResponseDto;
		
	}
	
	public TreatmentDto getServiceById(Long id) {
		Optional<Services> optionalService = treatmentRepo.findById(id);
		if(optionalService.isPresent()) {
			return optionalService.get().getServiceDto();
			
		}
		else {
			throw new EntityNotFoundException("Service not present");
		}
	}
	
	public boolean updateService(Long id, TreatmentDto treatmentDto) {
		Optional<Services> optionalService = treatmentRepo.findById(id);
		if(optionalService.isPresent()) {
			Services existingService =optionalService.get();
			existingService.setServiceName(treatmentDto.getServiceName());
			existingService.setPrice(treatmentDto.getPrice());
			existingService.setType(treatmentDto.getType());
			
			treatmentRepo.save(existingService);
			return true;
	}
		return false;
	}
	
	public void deleteService(Long id) {
		Optional<Services> optionlService = treatmentRepo.findById(id);
		if(optionlService.isPresent()) {
			treatmentRepo.deleteById(id);
		}
		else {
			throw new EntityNotFoundException("Service not present");
		}
	}
}
