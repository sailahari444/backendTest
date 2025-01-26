package com.myWebApplication.GlowStudio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myWebApplication.GlowStudio.entity.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
    
	Page<Booking> findAllByUserId(Pageable pageable, Long userId);
}
