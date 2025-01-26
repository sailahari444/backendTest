package com.myWebApplication.GlowStudio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myWebApplication.GlowStudio.entity.Services;

@Repository
public interface TreatmentRepo extends JpaRepository<Services, Long> {

	Page<Services> findByAvailability(boolean availability, Pageable pageable);
		
	
}
