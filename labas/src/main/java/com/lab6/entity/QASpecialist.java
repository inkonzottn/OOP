package com.lab6.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QASpecialist {
    private Long id;
    private String firstName;
    private String lastName;
    private String type; // Manual, Automation
}