package uz.pdp.project_1.controllers.university_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project_1.entities.university_entity.Subject;
import uz.pdp.project_1.playloads.university_playload.SubjectDto;
import uz.pdp.project_1.repositaries.university_repositary.SubjectRepositary;

import java.util.List;
import java.util.Optional;

@RestController
public class SubjectController {
    @Autowired
    SubjectRepositary subjectRepositary;

    @RequestMapping(value = "/subject", method = RequestMethod.GET)
    public List<Subject> getSubject() {
        return subjectRepositary.findAll();
    }

    @RequestMapping(value = "/subject/{id}", method = RequestMethod.GET)
    public Subject getSubjectById(@PathVariable int id) {
        Optional<Subject> optionalSubject = subjectRepositary.findById(id);
        return optionalSubject.orElse(null);
    }

    @RequestMapping(value = "/subject", method = RequestMethod.POST)
    public String addSubject(@RequestBody SubjectDto subjectDto) {
        subjectRepositary.save(new Subject(subjectDto.getName()));
        return "subject success added";
    }

    @RequestMapping(value = "/subject/{id}", method = RequestMethod.PUT)
    public String updateSubjectById(@PathVariable int id, @RequestBody SubjectDto subjectDto) {
        Optional<Subject> optionalSubject = subjectRepositary.findById(id);
        if (optionalSubject.isPresent()) {
            optionalSubject.get().setName(subjectDto.getName());
            subjectRepositary.save(optionalSubject.get());
            return "subject success updated";
        } else {
            return "subject note found";
        }
    }

    @RequestMapping(value = "/subject/{id}", method = RequestMethod.DELETE)
    public String deleteSubject(@PathVariable int id) {
        Optional<Subject> optionalSubject = subjectRepositary.findById(id);
        if (optionalSubject.isPresent()) {
            subjectRepositary.delete(optionalSubject.get());
            return "subject success deleted";
        } else {
            return "subject not found";
        }
    }
}
