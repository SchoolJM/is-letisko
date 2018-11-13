package airportis.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import airportis.app.entity.User;
import airportis.app.model.FlightModel;
import airportis.app.service.DestinationService;
import airportis.app.service.FlightService;
import airportis.app.service.GateService;
import airportis.app.service.PlaneService;
import airportis.app.service.UserService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	FlightService flightService;
	
	@Autowired
	DestinationService destinationService;
	
	@Autowired 
	PlaneService planseService;
	
	@Autowired 
	GateService gateService;
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public String showPanel(Model model) {
		User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("user", user);
		return "employee-panel";
	}
	
	@GetMapping("/addflight")
	public String showFlightForm(Model model) {
		model.addAttribute("flightModel", new FlightModel());
		model.addAttribute("destinationService", destinationService);
		model.addAttribute("planeService", planseService);
		model.addAttribute("gateService", gateService);
		return "addflight-formular";
	}

	@PostMapping("/addflight/process")
	public String processAddFlight(@Valid @ModelAttribute("flightModel") FlightModel flightModel, 
			BindingResult result, Model model) {
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "addflight-formular";
		}else {
			flightService.save(flightModel);
			model.addAttribute("addSuccess", true);
			return "redirect:/employee/addflight";
		}
	}
	
	@GetMapping("/updateflight")
	public String showUpdateFlightForm(
			@RequestParam(value="id", required=false) Integer id,
			Model model) {
		model.addAttribute("destinationService", destinationService);
		if(id == null) {
			model.addAttribute("flights", flightService.getAllFlights());
			return "updateflight";
		}
		FlightModel flightModel= flightService.getFlight(id.intValue());
		model.addAttribute("planeService", planseService);
		model.addAttribute("gateService", gateService);
		if(flightModel== null) {
			model.addAttribute("flightModel", new FlightModel());
			model.addAttribute("errorFlightNotFound", true);
		}else {
			System.out.println(flightModel);
			model.addAttribute("flightModel", flightModel);
		}
		return "updateflight-formular";
	}
	
	@PostMapping("/updateflight/process")
	public String processUpdateFlight(@Valid @ModelAttribute("flightModel") FlightModel flightModel, 
			BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "updateflight-formular";
		}else {
			System.out.println(flightModel.getId());
			flightService.save(flightModel);
			model.addAttribute("addSuccess", true);
			return "redirect:/employee/updateflight";		
		}
	}
}