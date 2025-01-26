package com.myWebApplication.GlowStudio.services.admin.bookings;

import com.myWebApplication.GlowStudio.dto.BookingResponseDto;

public interface BookedServices {
	
	BookingResponseDto getAllBookings(int pageNo);	
	
	boolean changeBookingStatus(Long id, String status);

}
