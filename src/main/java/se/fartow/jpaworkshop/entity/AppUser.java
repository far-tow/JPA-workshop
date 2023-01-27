package se.fartow.jpaworkshop.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Data
@EqualsAndHashCode
@ToString
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
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "details_id")
    private Details details;

    @OneToMany(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "borrower"
    )
    private List<BookLoan> loans;

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