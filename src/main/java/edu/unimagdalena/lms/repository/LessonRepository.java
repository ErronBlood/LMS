package edu.unimagdalena.lms.repository;

import edu.unimagdalena.lms.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<Lesson, UUID> {
    //lecciones por cursos ordenadas ascendentemente
    List<Lesson> findByCourse_IdOrderByOrderIndexAsc(UUID courseId);

    //Obtener lecciones por titulo (especificas)
    List<Lesson> findByTitleContainingIgnoreCase(String title);
}
