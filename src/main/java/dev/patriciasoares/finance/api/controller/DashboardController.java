package dev.patriciasoares.finance.api.controller;

import dev.patriciasoares.finance.api.dto.DashboardStatsDTO;
import dev.patriciasoares.finance.api.dto.MonthlyDataDTO;
import dev.patriciasoares.finance.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsDTO> getStats(){
        var stats = service.getDashboardStats();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/monthly-data")
    public ResponseEntity<List<MonthlyDataDTO>> getMonthlyData(){
        var monthlyData = service.getMonthlyData();
        return ResponseEntity.ok(monthlyData);
    }

    @GetMapping("/expenses-by-company")
    public ResponseEntity<List<MonthlyDataDTO>> getExpensesByCompany(){
        var expensesByCompany = service.getExpensesByCompany();
        return ResponseEntity.ok(expensesByCompany);
    }

    @GetMapping("/revenues-by-company")
    public ResponseEntity<List<MonthlyDataDTO>> getRevenuesByCompany(){
        var revenuesByCompany = service.getRevenuesByCompany();
        return ResponseEntity.ok(revenuesByCompany);
    }
} 