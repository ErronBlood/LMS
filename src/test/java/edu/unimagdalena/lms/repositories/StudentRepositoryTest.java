package edu.unimagdalena.lms.repositories;

import edu.unimagdalena.lms.entities.Assessment;
import edu.unimagdalena.lms.entities.Course;
import edu.unimagdalena.lms.entities.Enrollment;
import edu.unimagdalena.lms.entities.Student;
import edu.unimagdalena.lms.repository.AssessmentRepository;
import edu.unimagdalena.lms.repository.CourseRepository;
import edu.unimagdalena.lms.repository.EnrollmentRepository;
import edu.unimagdalena.lms.repository.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentRepositoryTest extends AbstractRepository{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    EnrollmentRepository enrollmentRepository;
    @Autowired
    AssessmentRepository assessmentRepository;

    @Test
    @DisplayName("Student: find all the students belonging to a specific course")
    void findStudentsByCourseTest(){
        //Given
        var course1 = Course.builder().title("perreo 101").active(true).build();
        var course2 = Course.builder().title("pavimentacion aerea 101").active(true).build();
        var student1 = Student.builder().fullName("pepe").email("pepe@gmail").build();
        var student2 = Student.builder().fullName("estefa").email("estefa@gmail").build();
        var enrollment1 = Enrollment.builder().student(student1).course(course1).build();
        var enrollment2 = Enrollment.builder().student(student2).course(course2).build();
        var enrollment3 =  Enrollment.builder().student(student1).course(course2).build();

        student1.getEnrollments().add(enrollment1);
        student1.getEnrollments().add(enrollment3);
        student2.getEnrollments().add(enrollment2);

        course1.getEnrollments().add(enrollment1);
        course2.getEnrollments().add(enrollment2);
        course2.getEnrollments().add(enrollment3);

        studentRepository.save(student1); studentRepository.save(student2);

        courseRepository.save(course1); courseRepository.save(course2);

        enrollmentRepository.save(enrollment1);enrollmentRepository.save(enrollment2);enrollmentRepository.save(enrollment3);

        //when
        List<Student> course1Students = studentRepository.findByEnrollments_Course_Id(course1.getId());
        List<Student> course2Students = studentRepository.findByEnrollments_Course_Id(course2.getId());

        //then
        assertThat(course1Students).hasSize(1);
        assertThat(course1Students).contains(student1);
        assertThat(course1Students).doesNotContain(student2);

        assertThat(course2Students).hasSize(2);
        assertThat(course2Students).containsExactlyInAnyOrder(student1, student2);

    }

    @Test
    @DisplayName("Student: Find all the passing students from a course")
    void findPassingStudentsByCourseTest(){

        var student1= Student.builder().fullName("pepe").email("pepe@gmail").build();
        var student2= Student.builder().fullName("pepa").email("pepa@gmail").build();
        var student3= Student.builder().fullName("Esternocleidomastoideo").email("eo@gmail").build();
        var student4= Student.builder().fullName("Kazuya Mishima").email("Tekkenistrash@gmail").build();

        var course1= Course.builder().title("perreo 101").active(true).build();

        var assessment1= Assessment.builder().type("exam").score(100).student(student1).course(course1).build();
        var assessment2= Assessment.builder().type("exam").score(500).student(student2).course(course1).build();
        var assessment3= Assessment.builder().type("exam").score(299).student(student3).course(course1).build();
        var assessment4= Assessment.builder().type("exam").score(300).student(student4).course(course1).build();

        student1.getAssessments().add(assessment1);
        student2.getAssessments().add(assessment2);
        student3.getAssessments().add(assessment3);
        student4.getAssessments().add(assessment4);

        studentRepository.save(student1); studentRepository.save(student2);
        studentRepository.save(student3); studentRepository.save(student4);

        courseRepository.save(course1);

        assessmentRepository.save(assessment1); assessmentRepository.save(assessment2);
        assessmentRepository.save(assessment3); assessmentRepository.save(assessment4);

        //When
        List<Student> students = studentRepository.findByPassingByCourseId(course1.getId());

        //Then
        assertThat(students).hasSize(2);
        assertThat(students).containsExactlyInAnyOrder(student2, student4);

    }

    @Test
    @DisplayName("Student: Find by Email (Ignore Case)")
    void emailTest(){
        //Given
        var student = Student.builder().email("pepitoperez@gmail").fullName("Pepito Perez").build();
        studentRepository.save(student);

        // When
        Optional<Student> byEmailNonExistentLower = studentRepository.findByEmailIgnoreCase("julian@demo.com");
        Optional<Student> byEmailExistentLower = studentRepository.findByEmailIgnoreCase("pepitoperez@gmail");
        Optional<Student> byEmailExistentMixed = studentRepository.findByEmailIgnoreCase("Pepitoperez@gmail");
        Optional<Student> byEmailExistentUpper = studentRepository.findByEmailIgnoreCase("PEPITOPEREZ@gmail");

        //Then

        assertThat(byEmailExistentLower).isPresent();
        assertThat(byEmailExistentMixed).isPresent();
        assertThat(byEmailExistentUpper).isPresent();
        assertThat(byEmailNonExistentLower).isNotPresent();
    }

    @Test
    @DisplayName("Student: Find student by their full name")
    void fullNameTest(){
        var student = Student.builder().fullName("Pepito Perez").email("elpepe@yahoo").build();

        studentRepository.save(student);

        Optional<Student> nameExistentUpper = studentRepository.findByFullNameIgnoreCase("Pepito Perez");
        Optional<Student> nameExistentLower = studentRepository.findByFullNameIgnoreCase("pepito perez");
        Optional<Student> nameNonExistent = studentRepository.findByFullNameIgnoreCase("Kazuya Mishima");

        assertThat(nameExistentUpper).isPresent();
        assertThat(nameExistentLower).isPresent();
        assertThat(nameNonExistent).isNotPresent();
    }




}
