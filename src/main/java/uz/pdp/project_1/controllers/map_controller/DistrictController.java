package uz.pdp.project_1.controllers.map_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project_1.entities.map_entity.District;
import uz.pdp.project_1.entities.map_entity.Region;
import uz.pdp.project_1.playloads.map_playload.DistrictDto;
import uz.pdp.project_1.repositaries.map_repositary.DistrictRepositary;
import uz.pdp.project_1.repositaries.map_repositary.RegionRepositary;

import java.util.List;
import java.util.Optional;

@RestController
public class DistrictController {
    @Autowired
    DistrictRepositary districtRepositary;

    @Autowired
    RegionRepositary regionRepositary;

    @RequestMapping(value = "/district", method = RequestMethod.GET)
    public List<District> getDistricts() {
        return districtRepositary.findAll();
    }

    @RequestMapping(value = "/district/{id}", method = RequestMethod.GET)
    public District getDistrictById(@PathVariable int id) {
        Optional<District> optionalDistrict = districtRepositary.findById(id);
        return optionalDistrict.orElse(null);
    }

    @RequestMapping(value = "/district", method = RequestMethod.POST)
    public String addDistrict(@RequestBody DistrictDto districtDto) {
        if (regionRepositary.findById(districtDto.getRegionId()).isPresent()) {
            districtRepositary.save(new District(districtDto.getName(), regionRepositary.findById(districtDto.getRegionId()).get()));
            return "district success added";
        } else {
            return "region not found please add new region then create this district";
        }
    }

    @RequestMapping(value = "/district/{id}", method = RequestMethod.PUT)
    public String updateDistrictById(@PathVariable int id, @RequestBody DistrictDto districtDto) {
        Optional<District> optionalDistrict = districtRepositary.findById(id);
        if (optionalDistrict.isPresent()) {
            Optional<Region> optionalRegion = regionRepositary.findById(districtDto.getRegionId());
            if (optionalRegion.isPresent()) {
                optionalDistrict.get().setName(districtDto.getName());
                optionalDistrict.get().setRegion(optionalRegion.get());
                districtRepositary.save(optionalDistrict.get());
                return "district success Updated";
            } else {
                return "Region not found please enter other Region";
            }
        } else {
            return "district not found";
        }
    }

    @RequestMapping(value = "/district/{id}", method = RequestMethod.DELETE)
    public String deleteDistrict(@PathVariable int id) {
        if (districtRepositary.findById(id).isPresent()) {
            districtRepositary.deleteById(id);
            return "district success deleted";
        } else {
            return "district not found";
        }
    }
}
