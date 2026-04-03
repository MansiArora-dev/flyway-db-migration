package com.springboot.flyway.dbmigration.repositories;

import com.springboot.flyway.dbmigration.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatus(String status);

    List<Order> findByUserId(Long userId);

}