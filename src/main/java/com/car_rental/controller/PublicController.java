package com.car_rental.controller;

import com.car_rental.dto.BookingRequest;
import com.car_rental.entity.Booking;
import com.car_rental.entity.Car;
import com.car_rental.enums.BookingStatus;
import com.car_rental.repository.BookingRepository;
import com.car_rental.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PublicController {

    private final CarService carService;
    private final BookingRepository bookingRepository;
    //constructor
    public PublicController(CarService carService, BookingRepository bookingRepository)  {
        this.carService = carService;
        this.bookingRepository = bookingRepository;
    }

    // homepage
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("cars", carService.getAllAvailableCars());
        model.addAttribute("bookingRequest", new BookingRequest());
        return "homepage";
    }

    // vehicle-gallery
    @GetMapping("/gallery")
    public String vehicleGallery(
        @RequestParam(name = "type", required = false) String type,
        @RequestParam(name = "start", required = false) LocalDate start, // Capture rental date from URL
        @RequestParam(name = "end", required = false) LocalDate end,     // Capture return date from URL
        Model model) {

        List<Car> cars;
        if (type == null || type.isEmpty() || type.equalsIgnoreCase("all")) {
            cars = carService.getAllAvailableCars();
        } else {
            cars = carService.getCarsByType(type);
        }

        model.addAttribute("cars", cars);
        model.addAttribute("activeType", type);
        
        // Now these variables exist and can be sent to the HTML
        model.addAttribute("startDate", start);
        model.addAttribute("endDate", end);

        return "vehicle-gallery";
    }

    
    @GetMapping("/details")
    public String viewDetails(
        @RequestParam(name = "id") Long id, 
        @RequestParam(name = "start", required = false) LocalDate start, 
        @RequestParam(name = "end", required = false) LocalDate end, 
        Model model) {

        Car selectedCar = carService.getCarById(id); 
        model.addAttribute("selectedCar", selectedCar);
        model.addAttribute("cars", carService.getAllAvailableCars());
        
        // CRITICAL: These must match the names used in your fragment call
        model.addAttribute("startDate", start);
        model.addAttribute("endDate", end);

        return "car_details";
    }
    @PostMapping("/book")
    public String processBooking(
        @ModelAttribute("bookingRequest") BookingRequest request,
        @RequestParam(name = "id", required = false) Long carId, // Fix: Added required = false
        RedirectAttributes redirectAttributes) {

        // 1. If carId is NULL, it's a search from the homepage, not a final booking
        if (carId == null) {
            // Redirect to gallery and pass the search criteria in the URL
            return "redirect:/gallery?type=" + request.getCarType() + 
                "&start=" + request.getRentalDate() + 
                "&end=" + request.getReturnDate();
        }

        // 2. Date Validation for actual booking
        if (!request.isValidDateRange()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Return date cannot be earlier than rental date.");
            return "redirect:/details?id=" + carId;
        }

        // 3. Fetch the specific car
        Car car = carService.getCarById(carId);
        if (car == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Car not found.");
            return "redirect:/gallery";
        }

        long rentalDays = java.time.temporal.ChronoUnit.DAYS.between(
        request.getRentalDate(), 
        request.getReturnDate()
        );
        if (rentalDays <= 0) rentalDays = 1; // Minimum 1 day charge

        // 4. Create and populate the Booking Entity
        Booking booking = new Booking();
        booking.setCar(car);
        booking.setStartDate(request.getRentalDate());
        booking.setEndDate(request.getReturnDate());
        booking.setBookingStatus(BookingStatus.PENDING);

        // 5. Calculate Total Price
        long days = java.time.temporal.ChronoUnit.DAYS.between(request.getRentalDate(), request.getReturnDate());
        if (days <= 0) days = 1; 
        
        java.math.BigDecimal totalPrice = car.getRentPrice().multiply(java.math.BigDecimal.valueOf(days));
        booking.setTotalPrice(totalPrice);

        // 6. Save using the instance variable (ensure it is injected in constructor)
        Booking savedBooking = bookingRepository.save(booking);

        // 7. Success Message
       redirectAttributes.addFlashAttribute("successMessage", "Booking initialized! Total: Rs " + totalPrice);
       return "redirect:/checkout?bookingId=" + savedBooking.getBookingId();
    }

    //checkout
    @GetMapping("/checkout")
    public String showCheckout(@RequestParam("bookingId") Long bookingId, Model model) {        
    Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid booking ID"));

    long rentalDays = java.time.temporal.ChronoUnit.DAYS.between(
        booking.getStartDate(), booking.getEndDate());
    if (rentalDays <= 0) rentalDays = 1;

    model.addAttribute("booking", booking);
    model.addAttribute("rentalDays", rentalDays);
    return "checkout"; 
    }

   @GetMapping("/success")
    public String showSuccess(@RequestParam Long bookingId, Model model) {
    // Fetch the booking from the database
    Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));

    // Calculate days for the "Duration" section
    long days = java.time.temporal.ChronoUnit.DAYS.between(booking.getStartDate(), booking.getEndDate());
    if (days <= 0) days = 1;

    model.addAttribute("booking", booking);
    model.addAttribute("rentalDays", days);
    
    return "success";
    }

    @Controller
public class AboutUsController {

    @GetMapping("/about")
    public String getAboutPage(Model model) {
        model.addAttribute("title", "About Us - Premium Car Rental");
        model.addAttribute("heroTitle", "Our Story");
        model.addAttribute("happyCustomers", "25K+");
        model.addAttribute("carCount", "600+");
        model.addAttribute("experienceYears", "30+");
        return "about-us";
        }
    }

    @Controller
    public class ContactController {

    @GetMapping("/contact")
    public String showContactPage() {
        return "contact-us";
        }
    }
    @PostMapping("/confirm-booking")
public String confirmBooking(@RequestParam("bookingId") Long bookingId, RedirectAttributes redirectAttributes) {
    // 1. Retrieve the pending booking
    Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new IllegalArgumentException("Booking not found: " + bookingId));

    // 2. Update status from PENDING to CONFIRMED
    booking.setBookingStatus(BookingStatus.CONFIRMED);

    // 3. Save the updated booking to the database
    bookingRepository.save(booking);

    // 4. Redirect to the success page with the ID
    return "redirect:/success?bookingId=" + bookingId;
}
}