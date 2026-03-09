package edu.unimagdalena.lms.repositories;

import edu.unimagdalena.lms.entities.Student;
import edu.unimagdalena.lms.repository.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StudentRepositoryTest extends AbstractRepository{
    @Autowired
    StudentRepository studentRepository;

    @Test
    @DisplayName("Student: Find by Email (Ignore Case)")
    void emailTest(){
        //Given
        var student = Student.builder().email("pepitoperez@gmail").fullName("Pepito Perez").build();
        studentRepository.save(student);

        // When
        Optional<Student> byEmailNonExistent = studentRepository.findByEmailIgnoreCase("julian@demo.com");
        Optional<Student> byEmailExistent = studentRepository.findByEmailIgnoreCase("pepitoperez@gmail");

        //then

        assertThat(byEmailExistent).isPresent();
        assertThat(byEmailNonExistent).isNotPresent();
    }
}
