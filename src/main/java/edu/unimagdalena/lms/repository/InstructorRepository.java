package edu.unimagdalena.lms.repository;

import edu.unimagdalena.lms.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface InstructorRepository extends JpaRepository<InstructorRepository, UUID> {

    Instructor findByEmail(String email);

    List<Instructor> findByFullNameContainingIgnoreCase(String fullName);

    @Query("SELECT DISTINCT i FROM Instructor i JOIN i.courses c WHERE c.active = TRUE")
    List<Instructor>findInstructorWithActiveCourses();

    @Query("SELECT i FROM Instructor i LEFT JOIN FETCH i.courses c ORDER BY c.createdAt DESC")
    List<Instructor> findAllWithCoursesOrdered();
}
