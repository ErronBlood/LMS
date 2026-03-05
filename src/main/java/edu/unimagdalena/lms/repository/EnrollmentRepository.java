package edu.unimagdalena.lms.repository;

import edu.unimagdalena.lms.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {

    List<Enrollment> findByStudent_Id(UUID studentId);

    List<Enrollment> findByCourse_Id(UUID courseId);

    Optional<Enrollment> findByStudent_IdAndCourse_Id(UUID studentId, UUID courseId);

    List<Enrollment> findByStatusAndEnrollmentAtBetween(String status, Instant from, Instant to);
}
