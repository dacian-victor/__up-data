package com.hanselnpetal.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="customer_contact")
public class CustomerContact {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;
	@Column(name = "first_name")
	private String firstName;
	private String lastName;
	private String email;
	private String deliveryAddressLine1;
	private String deliveryAddressLine2;
	private String deliveryAddressCity;
	private String deliveryAddressState;
	private String deliveryAddressLZipCode;
	
	public CustomerContact() {
	}

}
