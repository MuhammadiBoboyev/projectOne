package uz.pdp.project_1.controllers.university_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project_1.entities.university_entity.Groups;
import uz.pdp.project_1.entities.university_entity.Student;
import uz.pdp.project_1.entities.university_entity.Subject;
import uz.pdp.project_1.playloads.university_playload.StudentDto;
import uz.pdp.project_1.repositaries.university_repositary.GroupsRepositary;
import uz.pdp.project_1.repositaries.university_repositary.StudentRepositary;
import uz.pdp.project_1.repositaries.university_repositary.SubjectRepositary;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    StudentRepositary studentRepositary;

    @Autowired
    GroupsRepositary groupsRepositary;

    @Autowired
    SubjectRepositary subjectRepositary;

//    bu malumotni hammasini uzatadi bunda server qotadi shu bois pagable ko'rinishida uzatiladi
//    @RequestMapping(value = "/student", method = RequestMethod.GET)
//    public List<Student> getStudent() {
//        return studentRepositary.findAll();
//    }

    //    pagable ko'rinishida malumotlarni bo'lib bo'lib uzatish
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public Page<Student> getStudentPageable(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepositary.findAll(pageable);
    }

    @RequestMapping(value = "/studentByUniversity/{id}", method = RequestMethod.GET)
    public Page<Student> getStudentPageable(@PathVariable int id, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepositary.findAllByGroups_Faculty_UniversityId(id, pageable);
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public Student getStudentById(@PathVariable int id) {
        Optional<Student> optionalStudent = studentRepositary.findById(id);
        return optionalStudent.orElse(null);
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addStudent(@RequestBody StudentDto studentDto) {
        Optional<Groups> optionalGroups = groupsRepositary.findById(studentDto.getGroupsId());
        if (optionalGroups.isPresent()) {
            List<Subject> subjectList = subjectRepositary.findAllById(studentDto.getSubjects());
            if (subjectList.size() == studentDto.getSubjects().size()) {
                studentRepositary.save(
                        new Student(
                                studentDto.getFirstName(),
                                studentDto.getLastName(),
                                studentDto.getBirthDate(),
                                studentDto.getGender(),
                                optionalGroups.get(),
                                subjectList
                        )
                );
                return "student success added";
            } else {
                return "subject not found";
            }
        } else {
            return "Group not found please add new group then create new student";
        }
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public String updateStudentById(@PathVariable int id, @RequestBody StudentDto studentDto) {
        Optional<Student> optionalStudent = studentRepositary.findById(id);
        if (optionalStudent.isPresent()) {
            Optional<Groups> optionalGroups = groupsRepositary.findById(id);
            if (optionalGroups.isPresent()) {
                List<Subject> subjectList = subjectRepositary.findAllById(studentDto.getSubjects());
                if (subjectList.size() == studentDto.getSubjects().size()) {
                    optionalStudent.get().setFirstName(studentDto.getFirstName());
                    optionalStudent.get().setLastName(studentDto.getLastName());
                    optionalStudent.get().setBirthDate(studentDto.getBirthDate());
                    optionalStudent.get().setGender(studentDto.getGender());
                    optionalStudent.get().setGroups(optionalGroups.get());
                    optionalStudent.get().setSubjects(subjectList);
                    return "student success updated";
                } else {
                    return "subject not found";
                }
            } else {
                return "group not found";
            }
        } else {
            return "student not found";
        }
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public String deleteStudent(@PathVariable int id) {
        Optional<Student> optionalStudent = studentRepositary.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepositary.deleteById(id);
            return "student success deleted";
        } else {
            return "student not found";
        }
    }
}
