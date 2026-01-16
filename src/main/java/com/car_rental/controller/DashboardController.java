package com.car_rental.controller;

import com.car_rental.enums.BookingStatus;
import com.car_rental.repository.BookingRepository;
import com.car_rental.repository.CarRepository;
import com.car_rental.repository.CustomerRepository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;

    public DashboardController(CarRepository carRepository, 
                               CustomerRepository customerRepository, 
                               BookingRepository bookingRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
    }

   // Import java.math.BigDecimal;

   @GetMapping("/admin/dashboard")
    public String getDashboard(Model model) {
        BigDecimal revenue = bookingRepository.getTotalRevenue();
        
        model.addAttribute("totalRevenue", revenue != null ? revenue : BigDecimal.ZERO);
        model.addAttribute("totalBookings", bookingRepository.count());
        model.addAttribute("totalCustomers", customerRepository.count());
        model.addAttribute("totalCars", carRepository.count());

        // Pie Chart Data
        model.addAttribute("approvedCount", bookingRepository.countByBookingStatus(BookingStatus.CONFIRMED));
        model.addAttribute("pendingCount", bookingRepository.countByBookingStatus(BookingStatus.PENDING));

        // Revenue Chart Data (Linking to the new Repository method)
        List<BigDecimal> weeklyRevenue = bookingRepository.getWeeklyRevenue();
        model.addAttribute("weeklyRevenue", weeklyRevenue);

        return "admin/dashboard";
    }
}
