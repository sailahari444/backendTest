package com.myWebApplication.GlowStudio.services.customer.treatment;

import com.myWebApplication.GlowStudio.dto.ServiceResponseDto;

public interface SalonService {

	ServiceResponseDto getAvailableServices(int pageNo);
	
}
