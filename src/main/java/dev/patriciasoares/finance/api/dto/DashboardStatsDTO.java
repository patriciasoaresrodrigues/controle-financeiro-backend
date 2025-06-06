package dev.patriciasoares.finance.api.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DashboardStatsDTO {
    private BigDecimal totalRevenues;
    private BigDecimal totalExpenses;
    private BigDecimal totalPaidExpenses;
    private BigDecimal totalPendingExpenses;
    private BigDecimal totalPaidRevenues;
    private BigDecimal totalPendingRevenues;
    private BigDecimal balance;
    private Long totalRevenueCount;
    private Long totalExpenseCount;
    private Long paidExpenseCount;
    private Long pendingExpenseCount;
    private Long paidRevenueCount;
    private Long pendingRevenueCount;
} 