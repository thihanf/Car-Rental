package com.car_rental.controller;

import com.car_rental.entity.Booking;
import com.car_rental.enums.BookingStatus;
import com.car_rental.repository.BookingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AdminBookingController {

    private final BookingRepository bookingRepository;

    public AdminBookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/admin/bookings")
    public String bookings(Model model) {
        model.addAttribute("bookings", bookingRepository.findAll());
        model.addAttribute("activePage", "bookings");
        return "admin/bookings";
    }

    @PostMapping("/admin/bookings/approve/{id}")
    public String approveBooking(@PathVariable Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            // This updates the status from PENDING to CONFIRMED
            booking.setBookingStatus(BookingStatus.CONFIRMED); 
            bookingRepository.save(booking);
        }
        
        return "redirect:/admin/bookings";
    }
}