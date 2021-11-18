package uz.pdp.project_1.playloads.university_playload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.project_1.entities.university_entity.Subject;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDto {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Boolean gender;
    private int groupsId;
    private List<Integer> subjects;
}
