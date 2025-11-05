package com.lab4.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Developer {
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String position;
    private String qualification;
    private BigDecimal hourlyRate;
    private boolean isFree;
}
