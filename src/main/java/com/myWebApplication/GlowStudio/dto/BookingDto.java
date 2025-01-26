package com.myWebApplication.GlowStudio.dto;

import java.time.LocalDate;

import com.myWebApplication.GlowStudio.enums.BookingStatus;

public class BookingDto {

private Long id;
	
	private LocalDate bookingDate;
	
	private Long price;
	
	private BookingStatus bookingStatus;
	
	private Long serviceId;
	
	private String serviceType;
	
	private String serviceName;
	
	private Long userId;
	
	private String userName;

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

	public Long getServiceId() {
		return serviceId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public String getServiceName() {
		return serviceName;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
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

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
}
