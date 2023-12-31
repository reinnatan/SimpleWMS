package com.simple.warehouse.enitty;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rack")
public class Rack{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String name;
	
	@OneToMany(mappedBy = "rack",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Item> item;
	
	//@Column
	//private int count;
	
	//@Column
	//private Date dateInbound;
	
	//@Column
	//private Date dateOutbound;
	

	public Rack() {}

	public Rack(int id, String name, List<Item> item) {
		this.id = id;
		this.name = name;
		this.item = item;
		//this.count = count;
		//this.dateInbound = dateInbound;
		//this.dateOutbound = dateOutbound;
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


	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	
	
	
	
}