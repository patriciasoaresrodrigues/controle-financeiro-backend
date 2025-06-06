package dev.patriciasoares.finance.service;

import dev.patriciasoares.finance.api.dto.DashboardStatsDTO;
import dev.patriciasoares.finance.api.dto.MonthlyDataDTO;
import dev.patriciasoares.finance.repository.ExpenseRepository;
import dev.patriciasoares.finance.repository.RevenueRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    private final ExpenseRepository expenseRepository;
    private final RevenueRepository revenueRepository;

    public DashboardService(ExpenseRepository expenseRepository, RevenueRepository revenueRepository) {
        this.expenseRepository = expenseRepository;
        this.revenueRepository = revenueRepository;
    }

    public DashboardStatsDTO getDashboardStats() {
        var expenses = expenseRepository.findAll();
        var revenues = revenueRepository.findAll();

        var totalExpenses = expenses.stream()
                .map(expense -> expense.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var totalPaidExpenses = expenses.stream()
                .filter(expense -> expense.getPayDate() != null)
                .map(expense -> expense.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var totalPendingExpenses = expenses.stream()
                .filter(expense -> expense.getPayDate() == null)
                .map(expense -> expense.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var totalRevenues = revenues.stream()
                .map(revenue -> revenue.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var totalPaidRevenues = revenues.stream()
                .filter(revenue -> revenue.getPayDate() != null)
                .map(revenue -> revenue.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var totalPendingRevenues = revenues.stream()
                .filter(revenue -> revenue.getPayDate() == null)
                .map(revenue -> revenue.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var balance = totalPaidRevenues.subtract(totalPaidExpenses);

        return DashboardStatsDTO.builder()
                .totalExpenses(totalExpenses)
                .totalRevenues(totalRevenues)
                .totalPaidExpenses(totalPaidExpenses)
                .totalPendingExpenses(totalPendingExpenses)
                .totalPaidRevenues(totalPaidRevenues)
                .totalPendingRevenues(totalPendingRevenues)
                .balance(balance)
                .totalExpenseCount((long) expenses.size())
                .totalRevenueCount((long) revenues.size())
                .paidExpenseCount(expenses.stream().filter(e -> e.getPayDate() != null).count())
                .pendingExpenseCount(expenses.stream().filter(e -> e.getPayDate() == null).count())
                .paidRevenueCount(revenues.stream().filter(r -> r.getPayDate() != null).count())
                .pendingRevenueCount(revenues.stream().filter(r -> r.getPayDate() == null).count())
                .build();
    }

    public List<MonthlyDataDTO> getMonthlyData() {
        var expenses = expenseRepository.findAll();
        var revenues = revenueRepository.findAll();

        var monthlyExpenses = expenses.stream()
                .collect(Collectors.groupingBy(
                        expense -> expense.getDueDate().getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")) + "/" + expense.getDueDate().getYear(),
                        Collectors.reducing(BigDecimal.ZERO, expense -> expense.getAmount(), BigDecimal::add)
                ));

        var monthlyRevenues = revenues.stream()
                .collect(Collectors.groupingBy(
                        revenue -> revenue.getDueDate().getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")) + "/" + revenue.getDueDate().getYear(),
                        Collectors.reducing(BigDecimal.ZERO, revenue -> revenue.getAmount(), BigDecimal::add)
                ));

        var result = monthlyExpenses.entrySet().stream()
                .map(entry -> MonthlyDataDTO.builder()
                        .label("Despesas - " + entry.getKey())
                        .value(entry.getValue())
                        .build())
                .collect(Collectors.toList());

        monthlyRevenues.entrySet().stream()
                .map(entry -> MonthlyDataDTO.builder()
                        .label("Receitas - " + entry.getKey())
                        .value(entry.getValue())
                        .build())
                .forEach(result::add);

        return result;
    }

    public List<MonthlyDataDTO> getExpensesByCompany() {
        var expenses = expenseRepository.findAll();

        return expenses.stream()
                .collect(Collectors.groupingBy(
                        expense -> expense.getCompany(),
                        Collectors.reducing(BigDecimal.ZERO, expense -> expense.getAmount(), BigDecimal::add)
                ))
                .entrySet().stream()
                .map(entry -> MonthlyDataDTO.builder()
                        .label(entry.getKey())
                        .value(entry.getValue())
                        .build())
                .collect(Collectors.toList());
    }

    public List<MonthlyDataDTO> getRevenuesByCompany() {
        var revenues = revenueRepository.findAll();

        return revenues.stream()
                .collect(Collectors.groupingBy(
                        revenue -> revenue.getCompany(),
                        Collectors.reducing(BigDecimal.ZERO, revenue -> revenue.getAmount(), BigDecimal::add)
                ))
                .entrySet().stream()
                .map(entry -> MonthlyDataDTO.builder()
                        .label(entry.getKey())
                        .value(entry.getValue())
                        .build())
                .collect(Collectors.toList());
    }
} 