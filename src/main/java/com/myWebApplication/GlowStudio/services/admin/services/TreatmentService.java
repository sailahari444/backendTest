package com.myWebApplication.GlowStudio.services.admin.services;

import com.myWebApplication.GlowStudio.dto.ServiceResponseDto;
import com.myWebApplication.GlowStudio.dto.TreatmentDto;

public interface TreatmentService {

	boolean createService(TreatmentDto treatmentDto);
	
	ServiceResponseDto getAllServices(int pageNo);
	
	TreatmentDto getServiceById(Long id);
	
	boolean updateService(Long id, TreatmentDto treatmentDto);
	
	void deleteService(Long id);
}
