package dev.patriciasoares.finance.entity;

import dev.patriciasoares.finance.api.dto.RevenueDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "receitas")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "company")
    private String company;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "pay_date")
    private LocalDate payDate;
    @Column(name = "amount")
    private BigDecimal amount;

    public static Revenue getByDTO(RevenueDTO dto){
        return Revenue.builder()
                .id(dto.getId())
                .name(dto.getName())
                .company(dto.getCompany())
                .amount(dto.getAmount())
                .dueDate(dto.getDueDate())
                .payDate(dto.getPayDate())
                .build();
    }
}
