package com.lab6.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Manager {
    private Long id;
    private String firstName;
    private String lastName;
    private String position; // напр. Senior Project Manager
}