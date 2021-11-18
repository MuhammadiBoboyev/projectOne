package uz.pdp.project_1.repositaries.university_repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.project_1.entities.university_entity.Groups;
import uz.pdp.project_1.entities.university_entity.Subject;

public interface SubjectRepositary extends JpaRepository<Subject, Integer> {
}
