package com.apap.tutorial4.service;

import com.apap.tutorial4.model.FlightModel;

public interface FlightService {
	void addFlight(FlightModel pilot);
	void deleteFlight(FlightModel flight);
	void updateFlight(FlightModel flight);
	FlightModel getFlightDetailById(long Id);
}
