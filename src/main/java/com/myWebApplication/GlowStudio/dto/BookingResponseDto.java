package com.myWebApplication.GlowStudio.dto;

import java.util.List;

public class BookingResponseDto {
	
	private Integer totalPages;
	
	private Integer pageNo;
	
	private List<BookingDto> bookingDtoList;

	public Integer getTotalPages() {
		return totalPages;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public List<BookingDto> getBookingDtoList() {
		return bookingDtoList;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public void setBookingDtoList(List<BookingDto> bookingDtoList) {
		this.bookingDtoList = bookingDtoList;
	}
	
	

}
