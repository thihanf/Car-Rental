package com.car_rental.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.car_rental.entity.Booking;
import com.car_rental.enums.BookingStatus;
import com.car_rental.repository.BookingRepository;
import com.car_rental.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createBooking(Booking booking) {
        booking.setBookingStatus(BookingStatus.PENDING);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking updateBooking(Long bookingId, Booking booking) {
        Booking existingBooking = getBookingById(bookingId);

        existingBooking.setStartDate(booking.getStartDate());
        existingBooking.setEndDate(booking.getEndDate());
        existingBooking.setTotalPrice(booking.getTotalPrice());
        existingBooking.setBookingStatus(booking.getBookingStatus());

        return bookingRepository.save(existingBooking);
    }

    @Override
    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
