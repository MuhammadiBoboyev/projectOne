package uz.pdp.project_1.repositaries.university_repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.project_1.entities.university_entity.University;

public interface UniversityRepositary extends JpaRepository<University, Integer> {
}
