package uz.pdp.project_1.controllers.map_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project_1.entities.map_entity.Country;
import uz.pdp.project_1.entities.map_entity.Region;
import uz.pdp.project_1.playloads.map_playload.RegionDto;
import uz.pdp.project_1.repositaries.map_repositary.CountryRepositary;
import uz.pdp.project_1.repositaries.map_repositary.RegionRepositary;

import java.util.List;
import java.util.Optional;

@RestController
public class RegionController {
    @Autowired
    RegionRepositary regionRepositary;

    @Autowired
    CountryRepositary countryRepositary;

    @RequestMapping(value = "/region", method = RequestMethod.GET)
    public List<Region> getRegions() {
        return regionRepositary.findAll();
    }

    @RequestMapping(value = "/region/{id}", method = RequestMethod.GET)
    public Region getRegionById(@PathVariable int id) {
        Optional<Region> optionalRegion = regionRepositary.findById(id);
        return optionalRegion.orElse(null);
    }

    @RequestMapping(value = "/region", method = RequestMethod.POST)
    public String addRegion(@RequestBody RegionDto regionDto) {
        Optional<Country> optionalCountry = countryRepositary.findById(regionDto.getCountryId());
        if (optionalCountry.isPresent()) {
            regionRepositary.save(new Region(regionDto.getName(), optionalCountry.get()));
            return "country success added";
        } else {
            return "country not found please add new country then create this region";
        }
    }

    @RequestMapping(value = "/region/{id}", method = RequestMethod.PUT)
    public String updateRegionById(@PathVariable int id, @RequestBody RegionDto regionDto) {
        Optional<Region> optionalRegion = regionRepositary.findById(id);
        if (optionalRegion.isPresent()) {
            Optional<Country> optionalCountry = countryRepositary.findById(regionDto.getCountryId());
            if (optionalCountry.isPresent()) {
                optionalRegion.get().setName(regionDto.getName());
                optionalRegion.get().setCountry(optionalCountry.get());
                regionRepositary.save(optionalRegion.get());
                return "region success Updated";
            } else {
                return "country not found please enter other country";
            }
        } else {
            return "region not found";
        }
    }

    @RequestMapping(value = "/region/{id}", method = RequestMethod.DELETE)
    public String deleteRegion(@PathVariable int id) {
        if (regionRepositary.findById(id).isPresent()) {
            regionRepositary.deleteById(id);
            return "region success deleted";
        } else {
            return "region not found";
        }
    }
}
