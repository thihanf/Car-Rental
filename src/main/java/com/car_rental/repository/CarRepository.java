package com.car_rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car_rental.entity.Car;
import com.car_rental.enums.CarStatus;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByStatus(CarStatus status);
}
