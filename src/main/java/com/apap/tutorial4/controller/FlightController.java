package com.apap.tutorial4.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.FlightService;
import com.apap.tutorial4.service.PilotService;

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value="/flight/add/{licenseNumber}", method=RequestMethod.GET)
	private String add(@PathVariable(value="licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value="/flight/add", method=RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
		
	@RequestMapping(value="/flight/delete/{id}", method = RequestMethod.GET)
	private String deleteFlight(@PathVariable(value="id") String id) {
		FlightModel flight = flightService.getFlightDetailById(Long.parseLong(id));
		flightService.deleteFlight(flight);
		return "delete";
	}
	
	@RequestMapping(value="/flight/update/{id}", method = RequestMethod.GET)
	private String updateFlight(@PathVariable(value="id") String id, Model model) {
		FlightModel flight = flightService.getFlightDetailById(Long.parseLong(id));
		model.addAttribute("flight", flight);
		model.addAttribute("newFlight", new FlightModel());
		return "update-flight";
	}
	
	@RequestMapping(value="/flight/update", method = RequestMethod.POST)
	private String updatePilotSubmit(@ModelAttribute FlightModel flight) {
		flightService.updateFlight(flight);
		return "update";
	}
	
}
