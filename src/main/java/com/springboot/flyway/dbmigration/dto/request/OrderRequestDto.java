package com.springboot.flyway.dbmigration.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private Long userId;
    private List<OrderItemDto> items;
}