package com.car_rental.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.car_rental.entity.Car;
import com.car_rental.enums.CarStatus;
import com.car_rental.repository.CarRepository;
import com.car_rental.service.CarService;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getAllAvailableCars() {
        return carRepository.findByStatus(CarStatus.AVAILABLE);
    }
  
    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }
}
