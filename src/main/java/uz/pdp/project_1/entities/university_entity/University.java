package uz.pdp.project_1.entities.university_entity;

import lombok.*;
import uz.pdp.project_1.entities.map_entity.Address;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToOne(optional = false)
    private Address address;

    public University(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
