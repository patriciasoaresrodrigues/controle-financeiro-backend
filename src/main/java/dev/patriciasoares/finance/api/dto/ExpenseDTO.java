package dev.patriciasoares.finance.api.dto;

import dev.patriciasoares.finance.entity.Expense;
import dev.patriciasoares.finance.entity.Revenue;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class ExpenseDTO {
    private UUID id;
    private String name;
    private String company;
    private LocalDate dueDate;
    private LocalDate payDate;
    private BigDecimal amount;

    public static ExpenseDTO getByEntity(Expense expense){
        return ExpenseDTO.builder()
                .id(expense.getId())
                .name(expense.getName())
                .company(expense.getCompany())
                .amount(expense.getAmount())
                .dueDate(expense.getDueDate())
                .payDate(expense.getPayDate())
                .build();
    }
}
