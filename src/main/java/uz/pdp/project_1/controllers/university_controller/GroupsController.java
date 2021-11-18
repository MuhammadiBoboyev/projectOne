package uz.pdp.project_1.controllers.university_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project_1.entities.university_entity.Faculty;
import uz.pdp.project_1.entities.university_entity.Groups;
import uz.pdp.project_1.entities.university_entity.University;
import uz.pdp.project_1.playloads.university_playload.FacultyDto;
import uz.pdp.project_1.playloads.university_playload.GroupsDto;
import uz.pdp.project_1.repositaries.university_repositary.FacultyRepositary;
import uz.pdp.project_1.repositaries.university_repositary.GroupsRepositary;
import uz.pdp.project_1.repositaries.university_repositary.UniversityRepositary;

import java.security.acl.Group;
import java.util.List;
import java.util.Optional;

@RestController
public class GroupsController {
    @Autowired
    GroupsRepositary groupsRepositary;

    @Autowired
    FacultyRepositary facultyRepositary;

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public List<Groups> getGroups() {
        return groupsRepositary.findAll();
    }

    @RequestMapping(value = "/groups/{id}", method = RequestMethod.GET)
    public Groups getGroupsById(@PathVariable int id) {
        Optional<Groups> optionalGroups = groupsRepositary.findById(id);
        return optionalGroups.orElse(null);
    }

    @RequestMapping(value = "/groupsFacultyId{id}", method = RequestMethod.GET)
    public List<Groups> getGroups(@PathVariable int id) {
        return groupsRepositary.findAllByFacultyId(id);
    }

    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    public String addGroups(@RequestBody GroupsDto groupsDto) {
        Optional<Faculty> optionalFaculty = facultyRepositary.findById(groupsDto.getFacultyId());
        if (optionalFaculty.isPresent()) {
            groupsRepositary.save(new Groups(groupsDto.getName(), optionalFaculty.get()));
            return "groups success added";
        } else {
            return "faculty not found please add new Faculty then create new groups";
        }
    }

    @RequestMapping(value = "/groups/{id}", method = RequestMethod.PUT)
    public String updateGroupsById(@PathVariable int id, @RequestBody GroupsDto groupsDto) {
        Optional<Groups> optionalGroups = groupsRepositary.findById(id);
        if (optionalGroups.isPresent()) {
            Optional<Faculty> optionalFaculty = facultyRepositary.findById(groupsDto.getFacultyId());
            if (optionalFaculty.isPresent()) {
                optionalGroups.get().setName(groupsDto.getName());
                optionalGroups.get().setFaculty(optionalFaculty.get());
                groupsRepositary.save(optionalGroups.get());
                return "groups success updated";
            } else {
                return "faculty not found please enter other faculty";
            }
        } else {
            return "groups note found";
        }
    }

    @RequestMapping(value = "/groups/{id}", method = RequestMethod.DELETE)
    public String deleteGroups(@PathVariable int id) {
        if (groupsRepositary.findById(id).isPresent()) {
            groupsRepositary.deleteById(id);
            return "groups success deleted";
        } else {
            return "groups not found";
        }
    }
}
