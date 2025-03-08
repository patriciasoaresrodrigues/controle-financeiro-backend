package dev.patriciasoares.finance.repository;

import dev.patriciasoares.finance.entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RevenueRepository extends JpaRepository<Revenue, UUID> {
}
