package com.car_rental.service;

import com.car_rental.entity.Car;
import java.util.List;


public interface CarService {

    List<Car> getAllCars();

    List<Car> getAllAvailableCars();
    
    Car getCarById(Long id);

    Car saveCar(Car car);
}