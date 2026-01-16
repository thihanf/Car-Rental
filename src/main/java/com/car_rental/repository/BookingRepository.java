package com.car_rental.repository;

import com.car_rental.entity.Booking;
import com.car_rental.enums.BookingStatus;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Count for Pie Chart
    long countByBookingStatus(BookingStatus status);

    // Sum for Revenue Card
    @Query("SELECT SUM(b.totalPrice) FROM Booking b WHERE b.bookingStatus = 'CONFIRMED'")
    BigDecimal getTotalRevenue();

    @Query(value = "SELECT SUM(total_price) FROM bookings " + 
    "WHERE booking_status = 'CONFIRMED' " +
    "GROUP BY WEEK(start_date)", nativeQuery = true) 
    List<BigDecimal> getWeeklyRevenue();
}
