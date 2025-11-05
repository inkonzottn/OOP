package com.lab4.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
    private Long id;
    private String name;
    private String lastname;
    private String email;

    @ToString.Exclude // для уникнення рекурсії
    List<Project> projects;
}
