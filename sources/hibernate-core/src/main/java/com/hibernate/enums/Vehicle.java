package com.hibernate.enums;

import org.hibernate.boot.model.naming.IllegalIdentifierException;

public enum Vehicle {

	BUS("bus"), CAR("car"), TRAIN("train"), PLANE("plane");
	
	private String shortName;
	
	private Vehicle(String shortName) {
		this.shortName = shortName;
	}
	
	public String getShortName() {
		return this.shortName;
	}
	
	public static Vehicle fromShortName(String shortName) {
		switch(shortName) {
		case "bus":
			return Vehicle.BUS;
		case "car":
			return Vehicle.CAR;
		case "train":
			return Vehicle.TRAIN;
		case "plane":
			return Vehicle.PLANE;
		
		default:
			throw new IllegalIdentifierException("short name is not supported");
		}
	}
}
