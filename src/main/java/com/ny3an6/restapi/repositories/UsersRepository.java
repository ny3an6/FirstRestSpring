package com.ny3an6.restapi.repositories;

import com.ny3an6.restapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findAllByFirstName(String firstName);

    @Query(nativeQuery = true, value = "SELECT fix_user.*, fix_car.id as car_id, fix_car.model FROM fix_user LEFT JOIN fix_car ON fix_user.id = fix_car.owner_id")
    List<User> findUserWithCar();

    Optional<User> findByLogin(String login);
}
