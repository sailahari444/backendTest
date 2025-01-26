package com.myWebApplication.GlowStudio.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myWebApplication.GlowStudio.dto.TreatmentDto;
import com.myWebApplication.GlowStudio.services.admin.services.TreatmentService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/admin")
public class TreatmentController {

	private final TreatmentService treatmentService;

	public TreatmentController(TreatmentService treatmentService) {
		
		this.treatmentService = treatmentService;
	}
	
	@PostMapping("/services")
	public ResponseEntity<?> createRoom(@RequestBody TreatmentDto treatmentDto){
		boolean success = treatmentService.createService(treatmentDto);
		if(success){
			return ResponseEntity.status(HttpStatus.OK).build();
			
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/service/{pageNo}")
	public ResponseEntity<?> getAllServices(@PathVariable int pageNo){
		return ResponseEntity.ok(treatmentService.getAllServices(pageNo));
		
	}
	
	@GetMapping("/services/{id}")
	public ResponseEntity<?> getServiceById(@PathVariable Long id){
		try {
			return ResponseEntity.ok(treatmentService.getServiceById(id));
		}
		catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}
	
	@PutMapping("/services/{id}")
	public ResponseEntity<?> updateService(@PathVariable Long id, @RequestBody TreatmentDto treatmentDto){
		boolean success = treatmentService.updateService(id, treatmentDto);
		if(success) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@DeleteMapping("/services/{id}")
	public ResponseEntity<?> deleteService(@PathVariable Long id){
		try {
			treatmentService.deleteService(id);
			return ResponseEntity.ok(null);
		}
		catch(EntityNotFoundException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
