package se.fartow.jpaworkshop.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Data
@EqualsAndHashCode
@ToString(exclude = "appUser")
@Entity
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;
    @Column(nullable = false, length = 120, unique = true)
    private String email;
    private String name;
    @Column(nullable = false)
    private LocalDate birthDate;
    @OneToOne(mappedBy = "details")
    private AppUser appUser;

    public Details() {
    }
    public Details ( String email, String name, LocalDate birthDate) {
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
    }


}
