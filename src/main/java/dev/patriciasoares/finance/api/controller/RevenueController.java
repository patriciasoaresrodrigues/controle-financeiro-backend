package dev.patriciasoares.finance.api.controller;

import dev.patriciasoares.finance.api.dto.RevenueDTO;
import dev.patriciasoares.finance.service.RevenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/revenues")
public class RevenueController {
    private final RevenueService service;

    public RevenueController(RevenueService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RevenueDTO> create(@RequestBody RevenueDTO dto){
        System.out.println("controller" +dto.toString());
        var saved = service.save(dto);
        return ResponseEntity.ok(saved);
    }
    @PutMapping("{id}")
    public ResponseEntity<RevenueDTO> update(@PathVariable UUID id, @RequestBody RevenueDTO dto){
        var revenue = service.getById(id);
        revenue.setName(dto.getName());
        revenue.setAmount(dto.getAmount());
        revenue.setCompany(dto.getCompany());
        revenue.setDueDate(dto.getDueDate());
        revenue.setPayDate(dto.getPayDate());
        var saved = service.save(revenue);
        return ResponseEntity.ok(saved);
    }
    @GetMapping
    public ResponseEntity<List<RevenueDTO>> getAll(){
        var revenues = service.getAll();
        return ResponseEntity.ok(revenues);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        service.delete(id);
    }
    @GetMapping("{id}")
    public ResponseEntity<RevenueDTO> getById(@PathVariable UUID id){
        var revenue = service.getById(id);
        return ResponseEntity.ok(revenue);
    }
}
