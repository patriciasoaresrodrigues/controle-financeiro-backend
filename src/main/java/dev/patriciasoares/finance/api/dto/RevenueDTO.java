package dev.patriciasoares.finance.api.dto;

import dev.patriciasoares.finance.entity.Revenue;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
@Data
@Builder
public class RevenueDTO {
    private UUID id;
    private String name;
    private String company;
    private LocalDate dueDate;
    private LocalDate payDate;
    private BigDecimal amount;

    public static RevenueDTO getByEntity(Revenue revenue){
        return RevenueDTO.builder()
                .id(revenue.getId())
                .name(revenue.getName())
                .company(revenue.getCompany())
                .amount(revenue.getAmount())
                .dueDate(revenue.getDueDate())
                .payDate(revenue.getPayDate())
                .build();
    }
}
