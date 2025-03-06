package com.hibernate.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class VehicleConverter implements AttributeConverter<Vehicle, String> {

	@Override
	public String convertToDatabaseColumn(Vehicle vehicle) {
		return vehicle.getShortName();
	}

	@Override
	public Vehicle convertToEntityAttribute(String dbData) {
		return Vehicle.fromShortName(dbData);
	}

}
