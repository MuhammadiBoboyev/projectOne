package uz.pdp.project_1.controllers.map_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project_1.entities.map_entity.Address;
import uz.pdp.project_1.entities.map_entity.District;
import uz.pdp.project_1.playloads.map_playload.AddressDto;
import uz.pdp.project_1.repositaries.map_repositary.AddressRepositary;
import uz.pdp.project_1.repositaries.map_repositary.DistrictRepositary;

import java.util.List;
import java.util.Optional;

@RestController
public class AddressController {
    @Autowired
    AddressRepositary addressRepositary;

    @Autowired
    DistrictRepositary districtRepositary;

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public List<Address> getAddress() {
        return addressRepositary.findAll();
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.GET)
    public Address getAddressById(@PathVariable int id) {
        Optional<Address> optionalAddress = addressRepositary.findById(id);
        return optionalAddress.orElse(null);
    }

    @RequestMapping(value = "/address", method = RequestMethod.POST)
    public String addAddress(@RequestBody AddressDto addressDto) {
        if (districtRepositary.findById(addressDto.getDistrictId()).isPresent()) {
            addressRepositary.save(new Address(addressDto.getStreet(), addressDto.getHomeNumber(), districtRepositary.findById(addressDto.getDistrictId()).get()));
            return "address success added";
        } else {
            return "district not found please add new district then create this address";
        }
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.PUT)
    public String updateAddressById(@PathVariable int id, @RequestBody AddressDto addressDto) {
        Optional<Address> optionalAddress = addressRepositary.findById(id);
        if (optionalAddress.isPresent()) {
            Optional<District> optionalDistrict = districtRepositary.findById(addressDto.getDistrictId());
            if (optionalDistrict.isPresent()) {
                optionalAddress.get().setStreet(addressDto.getStreet());
                optionalAddress.get().setHomeNumber(addressDto.getHomeNumber());
                optionalAddress.get().setDistrict(optionalDistrict.get());
                return "address success Updated";
            } else {
                return "district not found please enter other district";
            }
        } else {
            return "address not found";
        }
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
    public String deleteAddress(@PathVariable int id) {
        if (addressRepositary.findById(id).isPresent()) {
            addressRepositary.deleteById(id);
            return "address success deleted";
        } else {
            return "address not found";
        }
    }
}
