package com.kodlamaio.rentACar.entities.concretes;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

	@Column(name = "returnDate")
	private LocalDate returnDate;

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
    @JoinColumn(name = "pick_up_city_id", referencedColumnName = "id")
    private City pickUpCityId;

    @ManyToOne
    @JoinColumn(name = "return_city_id", referencedColumnName = "id")
    private City returnCityId;
    
    @OneToOne(mappedBy = "rental")	
	private Invoice invoice;
	
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
