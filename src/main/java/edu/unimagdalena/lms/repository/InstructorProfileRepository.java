package edu.unimagdalena.lms.repository;

import edu.unimagdalena.lms.entities.InstructorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InstructorProfileRepository extends JpaRepository<InstructorProfile, UUID> {

    Optional<InstructorProfile> findByInstructor_Id(UUID instructorId);

    Optional<InstructorProfile> findByPhone(String phone);

    List<InstructorProfile> findByBioContainingIgnoreCase(String bioText);
}
