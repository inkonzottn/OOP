package com.lab6.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Developer {
    private Long id;
    private String firstName;
    private String lastName;
    private String specialization; // Java, Frontend, etc.
    private Double hourlyRate;
}