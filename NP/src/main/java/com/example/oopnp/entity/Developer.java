package com.example.oopnp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "developers")
@Data
@NoArgsConstructor
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Qualification qualification;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Specialization specialization;

    @Column(name = "hourly_rate")
    private Double hourlyRate;

    @Column(name = "image_url")
    private String imageUrl;

    // Пряме посилання на проект, над яким розробник працює зараз
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project currentProject;


    // Допоміжний метод для перевірки зайнятості
    public boolean isBusy() {
        return currentProject != null;
    }

    @ManyToMany
    @JoinTable(
            name = "developer_skills",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills;
}

// Enum для кваліфікації
enum Qualification {
    JUNIOR, MIDDLE, SENIOR, LEAD
}

enum Specialization {
    BACKEND, FRONTEND, QA, DEVOPS, DESIGN
}