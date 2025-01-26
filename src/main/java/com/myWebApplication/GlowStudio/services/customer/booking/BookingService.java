package com.myWebApplication.GlowStudio.services.customer.booking;

import com.myWebApplication.GlowStudio.dto.BookingDto;
import com.myWebApplication.GlowStudio.dto.BookingResponseDto;

public interface BookingService {

	boolean postBooking(BookingDto bookingDto);
	
	BookingResponseDto getAllBookingsByUserId(Long userId, int pageNo);
	
	void deleteService(Long id);
}
