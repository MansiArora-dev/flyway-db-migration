package com.springboot.flyway.dbmigration.dto.request;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderRequestDto {
    private Long userId;
    private List<OrderItemDto> items;

    @Data
    public static class OrderItemDto {
        private Long productId;
        private Integer quantity;
        private BigDecimal unitPrice;
    }
}