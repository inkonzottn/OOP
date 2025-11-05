package com.lab4.models;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;
    private String name;
    private String description;
    private boolean isCompleted = false;
    private Date createdAt;

    @ToString.Exclude // для уникнення рекурсії
    private Customer customer;
}
