package com.myWebApplication.GlowStudio.controller.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myWebApplication.GlowStudio.services.customer.treatment.SalonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class SalonController {

	private final SalonService salonService;

	public SalonController(SalonService salonService) {
		super();
		this.salonService = salonService;
	}
	
	@GetMapping("/treatment/{pageNo}")
	public ResponseEntity<?> getAvailableServices(@PathVariable int pageNo){
		return ResponseEntity.ok(salonService.getAvailableServices(pageNo));
	}
}
