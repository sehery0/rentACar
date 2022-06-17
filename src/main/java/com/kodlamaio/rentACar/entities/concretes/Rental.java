package com.kodlamaio.rentACar.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rentals")
public class Rental {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "pickUpDate")
	private LocalDate pickUpDate;

	@Column(name = "returnedDate")
	private LocalDate returnedDate;

	@Column(name = "totalDays")
	private int totalDays;

	@Column(name = "totalPrice")
	private double totalPrice;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	
	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
	
	@ManyToOne
    @JoinColumn(name = "pick_up_city", referencedColumnName = "id")
    private City pickUpCity;

    @ManyToOne
    @JoinColumn(name = "return_city", referencedColumnName = "id")
    private City returnCity;
	
	@OneToMany(mappedBy = "rental")
	private List<AdditionalService> addtionalServices;

}
