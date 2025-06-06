package dev.patriciasoares.finance.api.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MonthlyDataDTO {
    private String label;
    private BigDecimal value;
    private String month;
    private Integer year;
} 