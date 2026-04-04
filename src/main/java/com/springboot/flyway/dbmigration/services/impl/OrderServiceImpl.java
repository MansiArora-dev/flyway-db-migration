package com.springboot.flyway.dbmigration.services.impl;

import com.springboot.flyway.dbmigration.dto.request.OrderRequestDto;
import com.springboot.flyway.dbmigration.dto.response.OrderItemResponseDto;
import com.springboot.flyway.dbmigration.dto.response.OrderResponseDto;
import com.springboot.flyway.dbmigration.entities.Order;
import com.springboot.flyway.dbmigration.entities.OrderItem;
import com.springboot.flyway.dbmigration.entities.Product;
import com.springboot.flyway.dbmigration.entities.User;
import com.springboot.flyway.dbmigration.exceptions.ResourceNotFoundException;
import com.springboot.flyway.dbmigration.repositories.OrderRepository;
import com.springboot.flyway.dbmigration.repositories.ProductRepository;
import com.springboot.flyway.dbmigration.repositories.UserRepository;
import com.springboot.flyway.dbmigration.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return toResponse(order);
    }

    @Override
    public List<OrderResponseDto> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status).stream().map(this::toResponse).toList();
    }

    @Override
    public List<OrderResponseDto> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId).stream().map(this::toResponse).toList();
    }

    @Override
    public OrderResponseDto createOrder(OrderRequestDto request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        Order order = new Order();
        order.setUser(user);
        order.setStatus("PENDING");

        List<OrderItem> items = request.getItems().stream().map(itemDto -> {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + itemDto.getProductId()));
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());
            item.setUnitPrice(itemDto.getUnitPrice());
            return item;
        }).toList();

        order.setOrderItems(items);
        order.setTotalAmount(
                items.stream()
                        .map(i -> i.getUnitPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        return toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        order.setStatus(status);
        return toResponse(orderRepository.save(order));
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }

    private OrderResponseDto toResponse(Order order) {
        List<OrderItemResponseDto> itemDtos = order.getOrderItems() == null
                ? List.of()
                : order.getOrderItems().stream().map(item ->
                OrderItemResponseDto.builder()
                        .productId(item.getProduct().getId())
                        .productName(item.getProduct().getName())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .build()
        ).toList();

        return OrderResponseDto.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .userName(order.getUser().getName())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .items(itemDtos)
                .createdAt(order.getCreatedAt())
                .build();
    }
}