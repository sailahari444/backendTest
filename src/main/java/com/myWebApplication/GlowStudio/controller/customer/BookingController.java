package com.myWebApplication.GlowStudio.controller.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myWebApplication.GlowStudio.dto.BookingDto;
import com.myWebApplication.GlowStudio.services.customer.booking.BookingService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class BookingController {

     	private final BookingService bookingService;
     	
     	
     	
     	public BookingController(BookingService bookingService) {
			super();
			this.bookingService = bookingService;
		}



     	@PostMapping("/book")
		public ResponseEntity<?> postBooking(@RequestBody BookingDto bookingDto){
     		boolean success = bookingService.postBooking(bookingDto);
     		if(success) {
     			return ResponseEntity.status(HttpStatus.OK).build();
     		}
     		else {
     			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
     		}
     	}
     	
     	@GetMapping("/booking/{userId}/{pageNo}")
     	public ResponseEntity<?> getAllBookingsByUserId(@PathVariable Long userId, @PathVariable int pageNo){
     		try {
     				return ResponseEntity.ok(bookingService.getAllBookingsByUserId(userId, pageNo));
     		}
     		catch(Exception e) {
     			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
     		}
     	}
     	
     	@DeleteMapping("/booking/{id}")
     	public ResponseEntity<?> deleteService(@PathVariable Long id){
     		try {
     			bookingService.deleteService(id);
     			return ResponseEntity.ok(null);
     		}
     		catch(EntityNotFoundException e) {
     			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
     			
     		}
     	}
}
