package edu.unimagdalena.lms.repository;

import edu.unimagdalena.lms.entities.Course;
import edu.unimagdalena.lms.entities.Student;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

public interface StudentRepository extends Repository<Student, UUID> {

    @Query(value = "Select title from enrollments INNER JOIN courses on courses.id = enrollments.course_id Where (student_id = ?1)",
            nativeQuery = true)
    List<Course> findCoursesByStudent(UUID studentId);

    @Query(value = "Select Full_Name from enrollments INNER JOIN students on students.id = enrollments.students_id Where (course_id = ?1)",
            nativeQuery = true)
    List<Student> findStudentsByCourse(UUID courseId);

    @Query(value = "Select title from courses Where (instructor_id = ?1 AND active = true )",
            nativeQuery = true)
    List<Course> findCoursesByInstructor(UUID instructorId);

}
