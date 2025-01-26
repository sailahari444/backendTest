package com.myWebApplication.GlowStudio.services.customer.treatment;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myWebApplication.GlowStudio.dto.ServiceResponseDto;
import com.myWebApplication.GlowStudio.entity.Services;
import com.myWebApplication.GlowStudio.repository.TreatmentRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService{

	private final TreatmentRepo treatmentRepo;

	public SalonServiceImpl(TreatmentRepo treatmentRepo) {
		super();
		this.treatmentRepo = treatmentRepo;
	}
	
	
	public ServiceResponseDto getAvailableServices(int pageNo) {
		Pageable pageable = PageRequest.of(pageNo, 6);
		Page<Services> servicePage = treatmentRepo.findByAvailability(true, pageable);
		
		ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
		serviceResponseDto.setPageNo(servicePage.getPageable().getPageNumber());
		serviceResponseDto.setTotalPages(servicePage.getTotalPages());
		serviceResponseDto.setServiceDtoList(servicePage.stream().map(Services::getServiceDto).collect(Collectors.toList()));
		return serviceResponseDto;
		
	}
	
	
}
