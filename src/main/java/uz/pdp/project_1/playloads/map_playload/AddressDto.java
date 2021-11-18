package uz.pdp.project_1.playloads.map_playload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDto {
    private String street;
    private String homeNumber;
    private int districtId;
}
