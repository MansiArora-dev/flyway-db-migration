package com.springboot.flyway.dbmigration.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private String userName;
    private BigDecimal totalAmount;
    private String status;
    private List<OrderItemResponseDto> items;
    private LocalDateTime createdAt;
}