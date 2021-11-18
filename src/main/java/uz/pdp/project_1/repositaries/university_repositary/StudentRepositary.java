package uz.pdp.project_1.repositaries.university_repositary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.project_1.entities.university_entity.Student;

public interface StudentRepositary extends JpaRepository<Student, Integer> {
    Page<Student> findAllByGroups_Faculty_UniversityId(Integer groups_faculty_university_id, Pageable pageable);
}
