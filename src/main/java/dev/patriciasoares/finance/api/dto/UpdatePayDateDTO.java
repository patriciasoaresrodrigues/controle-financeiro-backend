package dev.patriciasoares.finance.api.dto;

import dev.patriciasoares.finance.entity.Expense;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class UpdatePayDateDTO {
    private UUID id;
    private LocalDate payDate;

    public static UpdatePayDateDTO getByEntity(Expense expense){
        return UpdatePayDateDTO.builder()
                .id(expense.getId())
                .payDate(expense.getPayDate())
                .build();
    }
}
