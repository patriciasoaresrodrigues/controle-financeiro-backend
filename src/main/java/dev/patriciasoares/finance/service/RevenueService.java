package dev.patriciasoares.finance.service;

import dev.patriciasoares.finance.api.dto.RevenueDTO;
import dev.patriciasoares.finance.entity.Revenue;
import dev.patriciasoares.finance.repository.RevenueRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class RevenueService {
    private final RevenueRepository repository;
    public RevenueService(RevenueRepository repository){
        this.repository = repository;
    }

    public RevenueDTO save(RevenueDTO dto){
        var revenue = Revenue.getByDTO(dto);
        System.out.println("create service" +revenue.toString());
        var saved = repository.save(revenue);
        dto.setId(saved.getId());
        return dto;
    }
    public List<RevenueDTO> getAll(){
        var revenues = repository.findAll();
        return revenues.stream().map(RevenueDTO::getByEntity).toList();
    }
    public void delete(UUID id){
        repository.deleteById(id);
    }
    public RevenueDTO getById(UUID id) {
        return repository.findById(id).map(RevenueDTO::getByEntity).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }
}
