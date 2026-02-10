package com.example.oopnp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "project_assignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "developer_id", nullable = false)
    @ToString.Exclude
    private Developer developer;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @ToString.Exclude
    private Project project;

    @Column(name = "hours_spent")
    private Integer hoursSpent = 0;

    @Column(name = "is_active")
    private boolean isActive = true; // чи працює він над цим прямо зараз
}
