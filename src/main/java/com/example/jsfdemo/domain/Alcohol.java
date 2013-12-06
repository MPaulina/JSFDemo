package com.example.jsfdemo.domain;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Alcohol {
	private String type; // jaki alkohol
	private String name; // nazwa marki
	private String code; // kod alkoholu
	private int quantun; // ilosc
	private double capacity; // pojemnosc
	private Date yop; //data przydatnosci piwa
	private boolean procentFree; /// standardowo jest false
	
	public Alcohol() {
	}

	public Alcohol(String type, String name, String code, int quantun,
			double capacity, Date yop, boolean procentFree) {
		super();
		this.type = type;
		this.name = name;
		this.code = code;
		this.quantun = quantun;
		this.capacity = capacity;
		this.yop = yop;
		this.procentFree = procentFree;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Pattern(regexp = "[A-Z][a-z]*")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Min(0)
	public int getQuantun() {
		return quantun;
	}

	public void setQuantun(int quantun) {
		this.quantun = quantun;
	}

	@NotNull(message = "Capacity NO NULL")
	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	
	public Date getYop() {
		return yop;
	}

	public void setYop(Date yop) {
		this.yop = yop;
	}

	public boolean isProcentFree() {
		return procentFree;
	}

	public void setProcentFree(boolean procentFree) {
		this.procentFree = procentFree;
	}
}
