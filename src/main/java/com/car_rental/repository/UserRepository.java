package com.car_rental.repository;

import com.car_rental.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.car_rental.enums.Role;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findByUsername(String username);
     boolean existsByUsername(String username);
     List<User> findByRoleIn(List<Role> roles);//filter roles
}
