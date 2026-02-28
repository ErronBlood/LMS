package edu.unimagdalena.lms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
    private String tittle;
    private String status;
    private Boolean active;
    @Column(name = "Created_At")
    private Instant createdAt;
    @Column(name = "Updated_At")
    private Instant updatedAt;

    @OneToMany(mappedBy = "courses")
    private Set<Lesson> lessons;


}
