package uz.pdp.project_1.entities.map_entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String homeNumber;

    @ManyToOne(optional = false)
    private District district;

    public Address(String street, String homeNumber, District district) {
        this.street = street;
        this.homeNumber = homeNumber;
        this.district = district;
    }
}
