package com.car_rental.dto;

import java.time.LocalDate;

public class BookingRequest {
    private String carType;
    private LocalDate rentalDate;
    private LocalDate returnDate;

    public boolean isValidDateRange() {
        if (rentalDate == null || returnDate == null) {
            return false;
        }
        return !returnDate.isBefore(rentalDate);
    }

    public String getCarType() { return carType; }
    public void setCarType(String carType) { this.carType = carType; }

    public LocalDate getRentalDate() { return rentalDate; }
    public void setRentalDate(LocalDate rentalDate) { this.rentalDate = rentalDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

}
