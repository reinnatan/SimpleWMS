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
	
	@Column(columnDefinition = "integer default 0")
	private int countItem;
	
	@Column(columnDefinition = "integer default 0")
	private int countInboundItem;
	
	@Column(columnDefinition = "integer default 0")
	private int countOutboundItem;
	
	@Column
	private Date dateInbound;
	
	@Column
	private Date dateOutbound;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rack_id")
	private Rack rack;
	

	public Item() {
	}

	public Item(int id, String name, Date dateProduction, int countItem) {
		this.id = id;
		this.name = name;
		this.dateProduction = dateProduction;
		this.countItem = countItem;
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

	public int getCountItem() {
		return countItem;
	}

	public void setCountItem(int countItem) {
		this.countItem = countItem;
	}

	public Date getDateInbound() {
		return dateInbound;
	}

	public void setDateInbound(Date dateInbound) {
		this.dateInbound = dateInbound;
	}

	public Date getDateOutbound() {
		return dateOutbound;
	}

	public void setDateOutbound(Date dateOutbound) {
		this.dateOutbound = dateOutbound;
	}

	public int getCountInboundItem() {
		return countInboundItem;
	}

	public void setCountInboundItem(int countInboundItem) {
		this.countInboundItem = countInboundItem;
	}

	public int getCountOutboundItem() {
		return countOutboundItem;
	}

	public void setCountOutboundItem(int countOutboundItem) {
		this.countOutboundItem = countOutboundItem;
	}

	
	
	
	
}