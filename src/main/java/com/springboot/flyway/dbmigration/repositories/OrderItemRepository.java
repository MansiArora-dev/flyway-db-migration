package com.springboot.flyway.dbmigration.repositories;

import com.springboot.flyway.dbmigration.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}