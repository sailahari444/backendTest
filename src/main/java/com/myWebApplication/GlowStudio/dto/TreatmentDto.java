package com.myWebApplication.GlowStudio.dto;

public class TreatmentDto {

    private Long id;
	
	private String serviceName;
	
	private String type;
	
	private Long price;
	
	private Boolean availability;

	public Long getId() {
		return id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getType() {
		return type;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getPrice() {
		return price;
	}

	public Boolean getAvailability() {
		return availability;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

	
	
	
	
}
