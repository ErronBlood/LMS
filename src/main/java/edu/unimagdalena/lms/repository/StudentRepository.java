package edu.unimagdalena.lms.repository;

import edu.unimagdalena.lms.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    List<Student> findByEnrollments_Course_Id(UUID courseId);

    @Query("SELECT s FROM Student s JOIN s.assessments a where a.course.id = ?1 and a.score >= 300")
    List<Student> findByPassingByCourseId(UUID courseId);

    Optional<Student> findByEmailIgnoreCase(String email);

    Student findByFullNameIgnoreCase(String fullName);
}
