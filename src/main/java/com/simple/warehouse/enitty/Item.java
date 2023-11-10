package com.simple.warehouse.enitty;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class Item{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String name;
	
	@Column
	private Date dateProduction;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rack_id")
	private Rack rack;
	

	public Item() {
	}

	public Item(int id, String name, Date dateProduction) {
		this.id = id;
		this.name = name;
		this.dateProduction = dateProduction;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateProduction() {
		return dateProduction;
	}

	public void setDateProduction(Date dateProduction) {
		this.dateProduction = dateProduction;
	}

	public void setRack(Rack rack) {
		this.rack = rack;
	}

	
	
}