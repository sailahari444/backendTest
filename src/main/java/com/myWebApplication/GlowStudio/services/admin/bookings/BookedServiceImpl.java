package com.myWebApplication.GlowStudio.services.admin.bookings;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myWebApplication.GlowStudio.dto.BookingResponseDto;
import com.myWebApplication.GlowStudio.entity.Booking;
import com.myWebApplication.GlowStudio.entity.Services;
import com.myWebApplication.GlowStudio.enums.BookingStatus;
import com.myWebApplication.GlowStudio.repository.BookingRepo;
import com.myWebApplication.GlowStudio.repository.TreatmentRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookedServiceImpl implements BookedServices {

	private final BookingRepo bookingRepo;
	
	private final TreatmentRepo treatmentRepo;
	
	public BookedServiceImpl(BookingRepo bookingRepo, TreatmentRepo treatmentRepo) {
		super();
		this.bookingRepo = bookingRepo;
		this.treatmentRepo =  treatmentRepo;
		
	}

	public static final int SEARCH_RESULT_PER_PAGE =4;
	
	public BookingResponseDto getAllBookings(int pageNo) {
		Pageable pageable = PageRequest.of(pageNo, SEARCH_RESULT_PER_PAGE);
		
		Page<Booking> bookingPage =  bookingRepo.findAll(pageable);
		
		BookingResponseDto bookingResponseDto = new BookingResponseDto();
		bookingResponseDto.setBookingDtoList(bookingPage.stream().map(Booking::getBookingDto).collect(Collectors.toList()));
		bookingResponseDto.setPageNo(bookingPage.getPageable().getPageNumber());
		bookingResponseDto.setTotalPages(bookingPage.getTotalPages());
		
		return bookingResponseDto;
		
		
		
	}
	
	public boolean changeBookingStatus(Long id, String status) {
		Optional<Booking> optionalBooking = bookingRepo.findById(id);
		if(optionalBooking.isPresent()) {
			Booking existingBooking = optionalBooking.get();
			if(Objects.equals(status, "Book")) {
				existingBooking.setBookingStatus(BookingStatus.BOOKED);
			}
			else {
				existingBooking.setBookingStatus(BookingStatus.REJECTED);
			}
			bookingRepo.save(existingBooking);
			
			Services existingService = existingBooking.getServices();
			existingService.setAvailability(true);
			
			treatmentRepo.save(existingService);
			return true;
		}
		return false;
	}
	
	
	
}
