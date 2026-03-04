package edu.unimagdalena.lms.repository;

import edu.unimagdalena.lms.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {

    List<Course> findByEnrollments_Student_Id(UUID studentId);

    Optional<Course>findByTitle(String title);

    //join c.instructor adds the information of related instructors, i after it works as an alias
    //to read information from them
    @Query("SELECT c FROM Course c JOIN c.instructor i WHERE c.active = TRUE AND i.id = ?1")
    List<Course> findActiveCoursesByInstructorId(UUID instructorId);

}
