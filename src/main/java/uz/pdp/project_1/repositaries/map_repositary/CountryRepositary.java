package uz.pdp.project_1.repositaries.map_repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.project_1.entities.map_entity.Country;

public interface CountryRepositary extends JpaRepository<Country, Integer> {
}
