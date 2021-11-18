package uz.pdp.project_1.repositaries.university_repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.project_1.entities.university_entity.Faculty;
import uz.pdp.project_1.entities.university_entity.Teacher;

import java.util.List;

public interface TeacherRepositary extends JpaRepository<Teacher, Integer> {
}
