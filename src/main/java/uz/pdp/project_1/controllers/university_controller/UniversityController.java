package uz.pdp.project_1.controllers.university_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project_1.entities.map_entity.Address;
import uz.pdp.project_1.entities.university_entity.University;
import uz.pdp.project_1.playloads.university_playload.UniversityDto;
import uz.pdp.project_1.repositaries.map_repositary.AddressRepositary;
import uz.pdp.project_1.repositaries.university_repositary.UniversityRepositary;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {
    @Autowired
    UniversityRepositary universityRepositary;

    @Autowired
    AddressRepositary addressRepositary;

    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getUniversity() {
        return universityRepositary.findAll();
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.GET)
    public University getUniversityById(@PathVariable int id) {
        Optional<University> optionalUniversity = universityRepositary.findById(id);
        return optionalUniversity.orElse(null);
    }

    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDto universityDto) {
        Optional<Address> optionalAddress = addressRepositary.findById(universityDto.getAddressId());
        if (optionalAddress.isPresent()) {
            universityRepositary.save(new University(universityDto.getName(), optionalAddress.get()));
            return "university success added";
        } else {
            return "address not found please add new address then create new university";
        }
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    public String updateUniversityById(@PathVariable int id, @RequestBody UniversityDto universityDto) {
        Optional<University> optionalUniversity = universityRepositary.findById(id);
        if (optionalUniversity.isPresent()) {
            Optional<Address> optionalAddress = addressRepositary.findById(universityDto.getAddressId());
            if (optionalAddress.isPresent()) {
                optionalUniversity.get().setName(universityDto.getName());
                optionalUniversity.get().setAddress(optionalAddress.get());
                universityRepositary.save(optionalUniversity.get());
                return "university success updated";
            } else {
                return "address not found please enter other address";
            }
        } else {
            return "university note found";
        }
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    public String deleteUniversity(@PathVariable int id) {
        if (universityRepositary.findById(id).isPresent()) {
            universityRepositary.deleteById(id);
            return "university success deleted";
        } else {
            return "university not found";
        }
    }
}
