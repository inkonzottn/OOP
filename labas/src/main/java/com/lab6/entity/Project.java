package com.lab6.entity;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    private Long id;
    private String title;
    private String technicalTask; // Текст ТЗ
    private Customer customer;
    private Manager manager;
    private Double totalPrice;

    // Списки учасників (імітація зв'язків)
    private List<Developer> developers;
    private List<QASpecialist> qaTeam;
}