package com.lab6.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectTeam {
    private Long id;
    private Long projectId;
    private Long employeeId; // ID розробника або тестувальника
    private String employeeRole; // "DEVELOPER" або "QA"
    private Integer hoursWorked;
}