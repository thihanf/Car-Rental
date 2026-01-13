package com.car_rental.service;

import java.util.List;

import com.car_rental.entity.Booking;

public interface BookingService {

    Booking createBooking(Booking booking);

    Booking getBookingById(Long bookingId);

    List<Booking> getAllBookings();

    Booking updateBooking(Long bookingId, Booking booking);

    void deleteBooking(Long bookingId);

}
