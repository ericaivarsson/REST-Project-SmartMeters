package de.tub.ise.anwsys.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product implements Serializable {

	private static final long serialVersionUID = -6640481949420444264L;

	@Id
	@GeneratedValue
	long id;
	String name;
	Double price;

	protected Product() {
		// empty constructor required by JPA
	}

	public Product(String name, Double price) {
		// TODO Auto-generated constructor stub

		// NOT SURE IF THIS IS CORRECT
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
