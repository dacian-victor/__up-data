package com.hanselnpetal.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="delivery_address")
public class DeliveryAddress {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;
	private String line1;
	private String line2;
	private String city;
	private String state;
	private String zipCode;

}
