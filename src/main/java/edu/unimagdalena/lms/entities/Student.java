package edu.unimagdalena.lms.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "Full_Name",  nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String email;
    @Column(name = "Created_At")
    private Instant createdAt;
    @Column(name = "Updated_At")
    private Instant UpdatedAt;

    @Builder.Default
    @OneToMany(mappedBy = "student")
    private Set<Enrollment> enrollments = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "student")
    private Set<Assessment> assessments = new HashSet<>();
}
