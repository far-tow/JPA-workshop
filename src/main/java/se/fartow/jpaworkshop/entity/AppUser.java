package se.fartow.jpaworkshop.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@EqualsAndHashCode
@ToString(exclude = "details")
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;
    @Column(nullable = false, length = 100, unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private LocalDate regDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private Details details;

    public AppUser() {
        this.regDate = LocalDate.now();
    }

    public AppUser(String userName, String password, Details details) {
        this();
        this.userName = userName;
        this.password = password;
        this.details = details;
    }
}