package uz.pdp.project_1.controllers.university_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project_1.entities.university_entity.Groups;
import uz.pdp.project_1.entities.university_entity.Student;
import uz.pdp.project_1.entities.university_entity.Subject;
import uz.pdp.project_1.entities.university_entity.Teacher;
import uz.pdp.project_1.playloads.university_playload.StudentDto;
import uz.pdp.project_1.playloads.university_playload.TeacherDto;
import uz.pdp.project_1.repositaries.university_repositary.GroupsRepositary;
import uz.pdp.project_1.repositaries.university_repositary.StudentRepositary;
import uz.pdp.project_1.repositaries.university_repositary.SubjectRepositary;
import uz.pdp.project_1.repositaries.university_repositary.TeacherRepositary;

import java.util.List;
import java.util.Optional;

@RestController
public class TeacherController {

    @Autowired
    TeacherRepositary teacherRepositary;

    @Autowired
    SubjectRepositary subjectRepositary;

    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public List<Teacher> getTeacher() {
        return teacherRepositary.findAll();
    }

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET)
    public Teacher getTeacherById(@PathVariable int id) {
        Optional<Teacher> optionalTeacher = teacherRepositary.findById(id);
        return optionalTeacher.orElse(null);
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.POST)
    public String addTeacher(@RequestBody TeacherDto teacherDto) {
        List<Subject> subjects = subjectRepositary.findAllById(teacherDto.getSubjects());
        if (subjects.size() == teacherDto.getSubjects().size()) {
            teacherRepositary.save(
                    new Teacher(
                            teacherDto.getFirstName(),
                            teacherDto.getLastName(),
                            teacherDto.getBirthDate(),
                            teacherDto.getGender(),
                            subjects
                    )
            );
            return "teacher success added";
        } else {
            return "subject not found";
        }
    }

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.PUT)
    public String updateTeacherById(@PathVariable int id, @RequestBody TeacherDto teacherDto) {
        Optional<Teacher> optionalTeacher = teacherRepositary.findById(id);
        if (optionalTeacher.isPresent()) {
            List<Subject> subjects = subjectRepositary.findAllById(teacherDto.getSubjects());
            if (subjects.size() == teacherDto.getSubjects().size()) {
                optionalTeacher.get().setFirstName(teacherDto.getFirstName());
                optionalTeacher.get().setLastName(teacherDto.getLastName());
                optionalTeacher.get().setBirthDate(teacherDto.getBirthDate());
                optionalTeacher.get().setGender(teacherDto.getGender());
                optionalTeacher.get().setSubjects(subjects);
                teacherRepositary.save(optionalTeacher.get());
                return "teacher success updated";
            } else {
                return "subject not found";
            }
        } else {
            return "teacher not found";
        }
    }

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.DELETE)
    public String deleteTeacher(@PathVariable int id) {
        Optional<Teacher> optionalTeacher = teacherRepositary.findById(id);
        if (optionalTeacher.isPresent()) {
            teacherRepositary.delete(optionalTeacher.get());
            return "teacher success deleted";
        } else {
            return "teacher not found";
        }
    }
}
