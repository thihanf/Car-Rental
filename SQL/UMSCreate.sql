INSERT INTO users (email, password, phone_no, role, username, status)
VALUES 
('admin@carrental.com', '$2a$10$7An3Yv3iAddvqjwtMkNcXe2pAJcj/5vXd7W6.s6x8Grq7OVvDUoDC', '0773727527', 'ROLE_ADMIN', 'admin', 'ACTIVE'),
('Thihanf2@gmail.com', '$2a$10$Eh8LtAYn4FwbKpnJMXwrMuYij3//unikmbBDv2l2P8gb9PRYc4mh6', NULL, 'ROLE_CUSTOMER', 'Thihan', 'ACTIVE'),
('admin2@gmail.com', '$2a$10$BOKSQGH8d.aqVuu0Asp0SuJskxvfAjJn5Y7X2SZr2isWDv5q3MjOC', '0770067890', 'ROLE_ADMIN', 'admin2', 'ACTIVE'),
('lsuruf2@gmail.com', '$2a$10$XiXwG1A8AihEv6.gWJw4JOYMNaJz.0yi5yt47mRK9zNj4Jm/UbvT.', NULL, 'ROLE_CUSTOMER', 'isuruf2', 'ACTIVE'),
('Nimalf007@gmail.com', '$2a$10$gFBGms4HEa/mIXcfRgeKJu7VmdiSlUZuKL4.t44QLZr2kWpSuDXN.', NULL, 'ROLE_CUSTOMER', 'Nimalf', 'ACTIVE');

INSERT INTO customers (user_id, first_name, last_name, email, phone)
VALUES 
(2, 'Thihan', 'Fernando', 'Thihanf2@gmail.com', '0762839091'),
(4, 'Isuru', 'Fernando', 'Isuruf2@gmail.com', '0763838081'),
(5, 'Nimal', 'Fernando', 'Nimalf007@gmail.com', '0762837071');

INSERT INTO cars (brand, model, type, rent_price, status, image_url, transmission, fuel_type, air_conditioner, seats)
VALUES 
('Honda', 'City 2003', 'Sedan', 5000.00, 'available', '/images/cars/Honda City 2003 Sedan.jpeg', 'Manual', 'Petrol', 1, 5),
('Mahindra', 'Bolero 2016', 'Van', 6000.00, 'available', '/images/cars/Mahindra Bolero 2016 Van.jpeg', 'Manual', 'Diesel', 1, 7),
('Mahindra', 'KUV 100 2021', 'SUV', 7500.00, 'available', '/images/cars/Mahindra KUV 100 2021  suv .jpeg', 'Manual', 'Petrol', 1, 5),
('Mazda', 'Axela 2001', 'Sedan', 5500.00, 'available', '/images/cars/Mazda Axela 2001 Sedan.jpeg', 'Automatic', 'Petrol', 1, 5),
('Mazda', 'Familia 2005', 'Sedan', 5000.00, 'available', '/images/cars/Mazda Familia-2005 sedan .jpeg', 'Manual', 'Petrol', 1, 5),
('Mitsubishi', 'Minicab 2020', 'Van', 4500.00, 'available', '/images/cars/Mitshubishi Minicab 2020 Van.jpeg', 'Manual', 'Petrol', 1, 2),
('Mitsubishi', 'Pajero 2012', 'SUV', 8500.00, 'available', '/images/cars/Mitshubishi Pajero 2012 suv.jpeg', 'Automatic', 'Diesel', 1, 7),
('Nissan', 'Caravan 2000', 'Van', 6500.00, 'available', '/images/cars/Nissan Caravan 2000 Van.jpeg', 'Manual', 'Diesel', 1, 12),
('Nissan', 'D21 1994', 'Pickup', 5500.00, 'available', '/images/cars/Nissan D21 1994 - pickup.jpeg', 'Manual', 'Diesel', 0, 3),
('Nissan', 'NV200 2019', 'Minivan', 7500.00, 'available', '/images/cars/Nissan NV200 2019 minivan.jpeg', 'Automatic', 'Petrol', 1, 7),
('Nissan', 'Serena 1997', 'Van', 5000.00, 'available', '/images/cars/Nissan Serena 1997 Van.jpeg', 'Manual', 'Petrol', 1, 7),
('Nissan', 'Sunny 2000', 'Sedan', 4500.00, 'available', '/images/cars/Nissan Sunny 2000 sedan.jpeg', 'Manual', 'Petrol', 1, 5),
('Toyota', 'Prius 2015', 'Sedan', 7000.00, 'available', '/images/cars/prius 2015 sedan.jpg', 'Automatic', 'Hybrid', 1, 5),
('Suzuki', 'Every 2014', 'Minivan', 4000.00, 'available', '/images/cars/Suzuki Every 2014 minivan.jpeg', 'Manual', 'Petrol', 1, 4),
('Tata', 'Xenon 2015', 'Pickup', 6500.00, 'available', '/images/cars/Tata Xenon 2015 pickup.jpeg', 'Manual', 'Diesel', 1, 5),
('Toyota', 'Alphard 2015', 'Minivan', 9500.00, 'available', '/images/cars/Toyota Alphard 2015 minivan.jpeg', 'Automatic', 'Petrol', 1, 8),
('Toyota', 'Hiace 1990', 'Van', 6000.00, 'available', '/images/cars/Toyota Hiace 1990 Van.jpeg', 'Manual', 'Diesel', 1, 12),
('Toyota', 'Hilux 1990', 'Pickup', 5500.00, 'available', '/images/cars/Toyota Hilux 1990 pickup .jpeg', 'Manual', 'Diesel', 0, 3),
('Toyota', 'Hilux 2019', 'Pickup', 9000.00, 'available', '/images/cars/Toyota Hilux 2019 - pickup.jpeg', 'Manual', 'Diesel', 1, 5),
('Toyota', 'Prado 2015', 'SUV', 11000.00, 'available', '/images/cars/Toyota Prado 2015 suv.jpeg', 'Automatic', 'Diesel', 1, 7),
('Toyota', 'Raize Z 2019', 'SUV', 8000.00, 'available', '/images/cars/Toyota Raize Z Grade 2019 suv.jpeg', 'Automatic', 'Petrol', 1, 5),
('Toyota', 'Voxy Hybrid 2025', 'Minivan', 12000.00, 'available', '/images/cars/Toyota Voxy Hybrid 2025 minivan.jpeg', 'Automatic', 'Hybrid', 1, 7);

INSERT INTO bookings (booking_id, booking_status, start_date, end_date, total_price, car_id, user_id)
VALUES 
(20, 'CONFIRMED', '2026-01-17', '2026-01-19', 10000.00, 14, 1),
(21, 'CONFIRMED', '2026-01-15', '2026-01-23', 52000.00, 15, 2),
(22, 'CONFIRMED', '2026-01-16', '2026-01-20', 16000.00, 16, 3),
(23, 'CONFIRMED', '2026-01-13', '2026-01-18', 55000.00, 17, 4),
(24, 'CONFIRMED', '2026-01-15', '2026-01-16', 7500.00, 18, 5);