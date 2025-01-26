package com.myWebApplication.GlowStudio.entity;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.myWebApplication.GlowStudio.dto.BookingDto;
import com.myWebApplication.GlowStudio.enums.BookingStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate bookingDate;
	
	private Long price;
	
	private BookingStatus bookingStatus;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="service_id", nullable=false)
	@OnDelete(action =OnDeleteAction.CASCADE)
	private Services services;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="user_id", nullable=false)
	@OnDelete(action =OnDeleteAction.CASCADE)
	private User user;

	public Long getId() {
		return id;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public Long getPrice() {
		return price;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public Services getServices() {
		return services;
	}

	public User getUser() {
		return user;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public void setServices(Services services) {
		this.services = services;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public BookingDto getBookingDto() {
		BookingDto bookingDto = new BookingDto();
		bookingDto.setId(id);
		bookingDto.setPrice(price);
		bookingDto.setBookingDate(bookingDate);
		bookingDto.setBookingStatus(bookingStatus);
		
		bookingDto.setUserId(user.getId());
		bookingDto.setUserName(user.getUsername());
		
		bookingDto.setServiceId(services.getId());
		bookingDto.setServiceName(services.getServiceName());
		bookingDto.setServiceType(services.getType());
		
		return bookingDto;
		
	}
}
