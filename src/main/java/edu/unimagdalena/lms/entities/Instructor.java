package edu.unimagdalena.lms.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "Full_Name", nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String email;
    @Column(name = "Created_At")
    private Instant createdAt;
    @Column(name = "Updated_At")
    private Instant updatedAt;

    @OneToMany(mappedBy = "instructor")
    private Set<Course> courses;

    @OneToOne(mappedBy = "instructor")
    private InstructorProfile profile;
}
