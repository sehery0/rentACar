package com.kodlamaio.rentACar.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	@Column(name ="firstName")
	private String firstName;
	
	@Column(name ="lastName")
	private String lastName;
	
	@Column(name = "identityNumber")
	private String identityNumber;
	
	@Column(name ="email")
	private String email;
	
	@Column(name ="password")
	private String password;
	
	@Column(name = "birthDate")
	private LocalDate birthDate;
	
	@OneToMany(mappedBy = "user")
	private List<Rental> rentals;
	

}
