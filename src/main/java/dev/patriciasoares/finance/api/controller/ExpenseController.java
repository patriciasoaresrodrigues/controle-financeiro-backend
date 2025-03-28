package dev.patriciasoares.finance.api.controller;

import dev.patriciasoares.finance.api.dto.ExpenseDTO;
import dev.patriciasoares.finance.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ExpenseDTO> create(@RequestBody ExpenseDTO dto){
        System.out.println("controller" +dto.toString());
        var saved = service.save(dto);
        return ResponseEntity.ok(saved);
    }
    @PutMapping("{id}")
    public ResponseEntity<ExpenseDTO> update(@PathVariable UUID id, @RequestBody ExpenseDTO dto){
        var expense = service.getById(id);
        expense.setName(dto.getName());
        expense.setAmount(dto.getAmount());
        expense.setCompany(dto.getCompany());
        expense.setDueDate(dto.getDueDate());
        expense.setPayDate(dto.getPayDate());
        var saved = service.save(expense);
        return ResponseEntity.ok(saved);
    }
    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getAll(){
        var expenses = service.getAll();
        return ResponseEntity.ok(expenses);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        service.delete(id);
    }
    @GetMapping("{id}")
    public ResponseEntity<ExpenseDTO> getById(@PathVariable UUID id){
        var expense = service.getById(id);
        return ResponseEntity.ok(expense);
    }
}
