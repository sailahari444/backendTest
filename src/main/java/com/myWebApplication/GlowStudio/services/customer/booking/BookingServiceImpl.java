package com.myWebApplication.GlowStudio.services.customer.booking;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myWebApplication.GlowStudio.dto.BookingDto;
import com.myWebApplication.GlowStudio.dto.BookingResponseDto;
import com.myWebApplication.GlowStudio.entity.Booking;
import com.myWebApplication.GlowStudio.entity.Services;
import com.myWebApplication.GlowStudio.entity.User;
import com.myWebApplication.GlowStudio.enums.BookingStatus;
import com.myWebApplication.GlowStudio.repository.BookingRepo;
import com.myWebApplication.GlowStudio.repository.TreatmentRepo;
import com.myWebApplication.GlowStudio.repository.UserRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

	private final UserRepo userRepo;
	
	private final TreatmentRepo treatmentRepo;
	
	private BookingRepo bookingRepo;
	
	public static final int SEARCH_RESULT_PER_PAGE =4;

	public BookingServiceImpl(UserRepo userRepo, TreatmentRepo treatmentRepo, BookingRepo bookingRepo) {
		super();
		this.userRepo = userRepo;
		this.treatmentRepo = treatmentRepo;
		this.bookingRepo = bookingRepo;
	}
	
	public boolean postBooking(BookingDto bookingDto) {
		Optional<User> optionalUser = userRepo.findById(bookingDto.getUserId());
		Optional<Services> optionalService = treatmentRepo.findById(bookingDto.getServiceId());
		if(optionalService.isPresent() && optionalUser.isPresent()) {
			  Booking booking  = new Booking();
			  booking.setServices(optionalService.get());
			  booking.setUser(optionalUser.get());
			  booking.setBookingDate(bookingDto.getBookingDate());
			  booking.setBookingStatus(BookingStatus.PENDING);
			  booking.setPrice(optionalService.get().getPrice());
			  
			  bookingRepo.save(booking);
			  return true;
					  }
		return false;
	}
	
	public BookingResponseDto getAllBookingsByUserId(Long userId, int pageNo) {
Pageable pageable = PageRequest.of(pageNo, SEARCH_RESULT_PER_PAGE);
		
		Page<Booking> bookingPage =  bookingRepo.findAllByUserId(pageable, userId);
		
		BookingResponseDto bookingResponseDto = new BookingResponseDto();
		bookingResponseDto.setBookingDtoList(bookingPage.stream().map(Booking::getBookingDto).collect(Collectors.toList()));
		bookingResponseDto.setPageNo(bookingPage.getPageable().getPageNumber());
		bookingResponseDto.setTotalPages(bookingPage.getTotalPages());
		
		return bookingResponseDto;
	}
	
	public void deleteService(Long id) {
		Optional<Booking> optionalBooking = bookingRepo.findById(id);
		if(optionalBooking.isPresent()) {
			bookingRepo.deleteById(id);
		}
		
		else {
			throw new EntityNotFoundException("Booking not present");
		}
	}
}
