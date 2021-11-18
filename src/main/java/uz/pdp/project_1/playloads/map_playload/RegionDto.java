package uz.pdp.project_1.playloads.map_playload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegionDto {
    private String name;
    private int countryId;
}
