package uz.pdp.project_1.controllers.university_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project_1.entities.university_entity.Faculty;
import uz.pdp.project_1.entities.university_entity.University;
import uz.pdp.project_1.playloads.university_playload.FacultyDto;
import uz.pdp.project_1.repositaries.university_repositary.FacultyRepositary;
import uz.pdp.project_1.repositaries.university_repositary.UniversityRepositary;

import java.util.List;
import java.util.Optional;

@RestController
public class FacultyController {
    @Autowired
    UniversityRepositary universityRepositary;

    @Autowired
    FacultyRepositary facultyRepositary;

    @RequestMapping(value = "/faculty", method = RequestMethod.GET)
    public List<Faculty> getFaculty() {
        return facultyRepositary.findAll();
    }

    @RequestMapping(value = "/faculty/{id}", method = RequestMethod.GET)
    public Faculty getFacultyById(@PathVariable int id) {
        Optional<Faculty> optionalFaculty = facultyRepositary.findById(id);
        return optionalFaculty.orElse(null);
    }

    @RequestMapping(value = "/universityId/{id}", method = RequestMethod.GET)
    public List<Faculty> getFacultyByUniversityId(@PathVariable int id) {
        return facultyRepositary.findAllByUniversityId(id);
    }

    @RequestMapping(value = "/faculty", method = RequestMethod.POST)
    public String addFaculty(@RequestBody FacultyDto facultyDto) {
        Optional<University> optionalUniversity = universityRepositary.findById(facultyDto.getUniversityId());
        if (optionalUniversity.isPresent()) {
            facultyRepositary.save(new Faculty(facultyDto.getName(), optionalUniversity.get()));
            return "Faculty success added";
        } else {
            return "University not found please add new University then create new faculty";
        }
    }

    @RequestMapping(value = "/faculty/{id}", method = RequestMethod.PUT)
    public String updateFacultyById(@PathVariable int id, @RequestBody FacultyDto facultyDto) {
        Optional<Faculty> optionalFaculty = facultyRepositary.findById(id);
        if (optionalFaculty.isPresent()) {
            Optional<University> optionalUniversity = universityRepositary.findById(facultyDto.getUniversityId());
            if (optionalUniversity.isPresent()) {
                optionalFaculty.get().setName(facultyDto.getName());
                optionalFaculty.get().setUniversity(optionalUniversity.get());
                facultyRepositary.save(optionalFaculty.get());
                return "Faculty success updated";
            } else {
                return "University not found please enter other university";
            }
        } else {
            return "Faculty note found";
        }
    }

    @RequestMapping(value = "/faculty/{id}", method = RequestMethod.DELETE)
    public String deleteFaculty(@PathVariable int id) {
        if (facultyRepositary.findById(id).isPresent()) {
            universityRepositary.deleteById(id);
            return "faculty success deleted";
        } else {
            return "faculty not found";
        }
    }
}
