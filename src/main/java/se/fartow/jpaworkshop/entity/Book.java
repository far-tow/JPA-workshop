package se.fartow.jpaworkshop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode
@ToString
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int Id;
    @Column(nullable = false, unique = true)
    private String isbn;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private int maxLoanDays;
    @ManyToMany
    private Set<Author> authors;

   public Book(String isbn, String title, int maxLoanDays) {
       this.isbn = isbn;
       this.title = title;
       this.maxLoanDays = maxLoanDays;
   }

    public Book() {

    }
}
