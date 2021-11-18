package uz.pdp.project_1.controllers.map_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project_1.entities.map_entity.Continent;
import uz.pdp.project_1.playloads.map_playload.ContinentDto;
import uz.pdp.project_1.repositaries.map_repositary.ContinentRepositary;

import java.util.List;
import java.util.Optional;

@RestController
public class ContinentController {
    @Autowired
    ContinentRepositary continentRepositary;

    @RequestMapping(value = "/continent", method = RequestMethod.GET)
    public List<Continent> getContinent() {
        return continentRepositary.findAll();
    }

    @RequestMapping(value = "/continent/{id}", method = RequestMethod.GET)
    public Continent getContinentById(@PathVariable int id) {
        Optional<Continent> optionalContinent = continentRepositary.findById(id);
        return optionalContinent.orElse(null);
    }

    @RequestMapping(value = "/continent", method = RequestMethod.POST)
    public String addContinent(@RequestBody ContinentDto continentDto) {
        continentRepositary.save(new Continent(continentDto.getName()));
        return "continent success added";
    }

    @RequestMapping(value = "/continent/{id}", method = RequestMethod.PUT)
    public String updateContinentById(@PathVariable int id, @RequestBody ContinentDto continentDto) {
        Continent continent = getContinentById(id);
        if (continent == null) {
            return "continent not found";
        } else {
            continent.setName(continentDto.getName());
            continentRepositary.save(continent);
            return "continent success Updated";
        }
    }

    @RequestMapping(value = "/continent/{id}", method = RequestMethod.DELETE)
    public String deleteContinent(@PathVariable int id) {
        if (getContinentById(id) == null) {
            return "Continent not found";
        } else {
            continentRepositary.deleteById(id);
            return "continent success deleted";
        }
    }
}
