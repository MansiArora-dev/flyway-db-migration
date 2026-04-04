package com.springboot.flyway.dbmigration.services;

import com.springboot.flyway.dbmigration.dto.request.OrderRequestDto;
import com.springboot.flyway.dbmigration.dto.response.OrderResponseDto;
import java.util.List;

public interface OrderService {
    List<OrderResponseDto> getAllOrders();
    OrderResponseDto getOrderById(Long id);
    List<OrderResponseDto> getOrdersByStatus(String status);
    List<OrderResponseDto> getOrdersByUserId(Long userId);
    OrderResponseDto createOrder(OrderRequestDto request);
    OrderResponseDto updateOrderStatus(Long id, String status);
    void deleteOrder(Long id);
}