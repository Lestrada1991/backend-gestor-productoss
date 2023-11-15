package com.company.inventory.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Id;

@Data
@Entity
@Table(name="account")
public class Account implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 88414716843936509L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String accountName;
	private String direccion;
	private String whatsapp;
	private String contacto;
	

}
