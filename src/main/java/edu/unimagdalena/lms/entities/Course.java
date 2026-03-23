package edu.unimagdalena.lms.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String status;
    private Boolean active;
    @Column(name = "Created_At")
    private Instant createdAt;
    @Column(name = "Updated_At")
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Builder.Default
    @OneToMany(mappedBy = "course")
    private Set<Lesson> lessons = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "course")
    private Set<Enrollment> enrollments =  new HashSet<>();

}
