package uz.pdp.project_1.repositaries.university_repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.project_1.entities.university_entity.Faculty;
import uz.pdp.project_1.entities.university_entity.University;

import java.util.List;

public interface FacultyRepositary extends JpaRepository<Faculty, Integer> {
    List<Faculty> findAllByUniversityId(Integer id);
}
