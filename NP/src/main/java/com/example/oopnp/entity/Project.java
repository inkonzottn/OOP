package com.example.oopnp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description; // Тут зберігаємо зміст ТЗ

    @Column(name = "total_cost")
    private Double totalCost = 0.0;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status = ProjectStatus.PROPOSAL;

    // Зв'язок із замовником
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @ToString.Exclude
    private Customer customer;

    // Зв'язок із менеджером
    @ManyToOne
    @JoinColumn(name = "manager_id")
    @ToString.Exclude
    private Manager manager;

    // Список призначень розробників
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectAssignment> assignments;

    // Метод для розрахунку вартості
    public void calculateCost() {
        if (assignments != null) {
            this.totalCost = assignments.stream()
                    .mapToDouble(a -> a.getHoursSpent() * a.getDeveloper().getHourlyRate())
                    .sum();
        }
    }
}

enum ProjectStatus {
    PROPOSAL,   // ТЗ на розгляді
    IN_PROGRESS, // Розробники працюють
    COMPLETED,  // Роботу завершено
    INVOICED    // Рахунок виставлено замовнику
}
