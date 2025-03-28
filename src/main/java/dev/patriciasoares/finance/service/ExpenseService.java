package dev.patriciasoares.finance.service;

import dev.patriciasoares.finance.api.dto.ExpenseDTO;
import dev.patriciasoares.finance.entity.Expense;
import dev.patriciasoares.finance.repository.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ExpenseService {
    private final ExpenseRepository repository;
    public ExpenseService(ExpenseRepository repository){
        this.repository = repository;
    }

    public ExpenseDTO save(ExpenseDTO dto){
        var expense = Expense.getByDTO(dto);
        var saved = repository.save(expense);
        dto.setId(saved.getId());
        return dto;
    }
    public List<ExpenseDTO> getAll(){
        var expenses = repository.findAll();
        return expenses.stream().map(ExpenseDTO::getByEntity).toList();
    }
    public void delete(UUID id){
        repository.deleteById(id);
    }
    public ExpenseDTO getById(UUID id) {
        return repository.findById(id).map(ExpenseDTO::getByEntity).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }
}
