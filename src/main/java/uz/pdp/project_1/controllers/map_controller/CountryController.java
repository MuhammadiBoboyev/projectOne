package uz.pdp.project_1.controllers.map_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project_1.entities.map_entity.Continent;
import uz.pdp.project_1.entities.map_entity.Country;
import uz.pdp.project_1.playloads.map_playload.CountryDto;
import uz.pdp.project_1.repositaries.map_repositary.ContinentRepositary;
import uz.pdp.project_1.repositaries.map_repositary.CountryRepositary;

import java.util.List;
import java.util.Optional;

@RestController
public class CountryController {
    @Autowired
    CountryRepositary countryRepositary;

    @Autowired
    ContinentRepositary continentRepositary;

    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public List<Country> getCountries() {
        return countryRepositary.findAll();
    }

    @RequestMapping(value = "/country/{id}", method = RequestMethod.GET)
    public Country getCountryById(@PathVariable int id) {
        Optional<Country> optionalCountry = countryRepositary.findById(id);
        return optionalCountry.orElse(null);
    }

    @RequestMapping(value = "/country", method = RequestMethod.POST)
    public String addCountry(@RequestBody CountryDto countryDto) {
        Optional<Continent> optionalContinent = continentRepositary.findById(countryDto.getContinentId());

        if (optionalContinent.isPresent()) {
            countryRepositary.save(new Country(countryDto.getName(), optionalContinent.get()));
            return "country success added";
        } else {
            return "continent not found please add new Continent then create this country";
        }
    }

    @RequestMapping(value = "/country/{id}", method = RequestMethod.PUT)
    public String updateCountryById(@PathVariable int id, @RequestBody CountryDto countryDto) {
        Optional<Country> optionalCountry = countryRepositary.findById(id);
        if (optionalCountry.isPresent()) {
            Optional<Continent> optionalContinent = continentRepositary.findById(countryDto.getContinentId());
            if (optionalContinent.isPresent()) {
                optionalCountry.get().setName(countryDto.getName());
                optionalCountry.get().setContinent(optionalContinent.get());
                countryRepositary.save(optionalCountry.get());
                return "continent success Updated";
            } else {
                return "continent not found please enter other continent";
            }
        } else {
            return "country not found";
        }
    }

    @RequestMapping(value = "/country/{id}", method = RequestMethod.DELETE)
    public String deleteCountry(@PathVariable int id) {
        Optional<Country> optionalCountry = countryRepositary.findById(id);
        if (optionalCountry.isPresent()) {
            countryRepositary.deleteById(id);
            return "country not found";
        }
        return "country success deleted";
    }
}
