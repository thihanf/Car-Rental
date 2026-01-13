package com.car_rental.controller;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.car_rental.entity.Car;
import com.car_rental.service.CarService;

@Controller
@RequestMapping("/admin/vehicles")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    // ================= ADMIN VEHICLES TABLE =================
    @GetMapping
    public String listVehicles(Model model) {
        model.addAttribute("vehicles", carService.getAllCars());
        return "admin/vehicles";
    }

    // ================= SHOW ADD VEHICLE FORM =================
    @GetMapping("/add")
    public String showAddVehicleForm(Model model) {
        model.addAttribute("vehicle", new Car());
        return "admin/add-vehicle";
    }

    // ================= SHOW EDIT VEHICLE FORM =================
    // Show Edit Vehicle Form
    @GetMapping("/edit/{id}")
    public String showEditVehicleForm(@PathVariable Long id, Model model) {
        Car car = carService.getCarById(id);
        model.addAttribute("vehicle", car);
        return "admin/edit-vehicle";
    }

    // Update Vehicle
    @PostMapping("/update")
    public String updateVehicle(@ModelAttribute("vehicle") Car car) {
        carService.saveCar(car); // save() updates if ID exists
        return "redirect:/admin/vehicles";
    }

    

    // ================= SAVE VEHICLE (WITH IMAGE) =================
    @PostMapping("/add")
    public String addVehicle(
            @ModelAttribute("vehicle") Car car,
            @RequestParam("image") MultipartFile imageFile
    ) throws IOException {

        // 1️⃣ Save image if provided
        if (!imageFile.isEmpty()) {

            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();

            Path uploadPath = Paths.get("src/main/resources/static/images/cars");
            Files.createDirectories(uploadPath);

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(
                    imageFile.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            // 2️⃣ Save image URL to DB
            car.setImageUrl("/images/cars/" + fileName);
        }

        // 3️⃣ Save vehicle
        carService.saveCar(car);

        return "redirect:/admin/vehicles";
    }

    // ================= PUBLIC CARS PAGE =================
    @GetMapping("/cars")
    public String carsPage(Model model) {
        model.addAttribute("cars", carService.getAllAvailableCars());
        return "cars";
    }

    // ================= HOMEPAGE =================
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("cars", carService.getAllAvailableCars());
        return "homepage";
    }


}