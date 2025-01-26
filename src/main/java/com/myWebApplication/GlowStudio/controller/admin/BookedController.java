package com.myWebApplication.GlowStudio.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myWebApplication.GlowStudio.services.admin.bookings.BookedServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class BookedController {

	
	private final BookedServices bookedService;
	
	
	
	public BookedController(BookedServices bookedService) {
		super();
		this.bookedService = bookedService;
	}



	@GetMapping("/bookings/{pageNo}")
	public ResponseEntity<?> getAllBookings(@PathVariable int pageNo){
		try {
			return ResponseEntity.ok(bookedService.getAllBookings(pageNo));
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}
	
	@GetMapping("/bookings/{id}/{status}")
	public ResponseEntity<?> changeBookingStatus(@PathVariable Long id, @PathVariable String status){
		boolean success = bookedService.changeBookingStatus(id, status);
		if(success) {
			return ResponseEntity.ok().build();
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}
}
