package com.myWebApplication.GlowStudio.dto;

import java.util.List;

public class ServiceResponseDto {

	private List<TreatmentDto> serviceDtoList;
	
	private Integer totalPages;
	
	private Integer pageNo;

	public List<TreatmentDto> getServiceDtoList() {
		return serviceDtoList;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setServiceDtoList(List<TreatmentDto> serviceDtoList) {
		this.serviceDtoList = serviceDtoList;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	
	
}
