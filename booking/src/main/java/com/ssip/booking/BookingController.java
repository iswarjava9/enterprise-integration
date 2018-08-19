package com.ssip.booking;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingController {
	
	@GetMapping("/{id}")
	public String getBooking(@PathVariable("id") int id){
		
		return "Booking deatils with id ------------------- "+id;
	}

}
