package com.myWebApplication.GlowStudio.entity;

import com.myWebApplication.GlowStudio.dto.TreatmentDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Services")
public class Services {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "service_name")
	private String serviceName;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "price")
	private Long price;
	
	@Column(name = "availability")
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




	public Long getPrice() {
		return price;
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




	public void setPrice(Long price) {
		this.price = price;
	}

     


	public Boolean getAvailability() {
		return availability;
	}




	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}




	public TreatmentDto getServiceDto() {
		TreatmentDto treatmentDto = new TreatmentDto();
		treatmentDto.setId(id);
		treatmentDto.setServiceName(serviceName);
		treatmentDto.setType(type);
		treatmentDto.setPrice(price);
		treatmentDto.setAvailability(availability);
		
		return treatmentDto;
	}
	
}
