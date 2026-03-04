package edu.unimagdalena.lms.repository;

import edu.unimagdalena.lms.entities.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AssessmentRepository extends JpaRepository<Assessment, UUID> {

    @Query("SELECT a FROM Assessment a JOIN Student s WHERE s.id = :studentId AND a.score BETWEEN :downLimit and :upLimit")
    List<Assessment> findAssessmentInRangeByStudent(UUID studentId,
                                                    @Param("downLimit") int downLimit,
                                                    @Param("upLimit") int upLimit);

    Optional<Assessment> findByStudent_IdAndCourse_Id(UUID studentId, UUID courseId);
}
