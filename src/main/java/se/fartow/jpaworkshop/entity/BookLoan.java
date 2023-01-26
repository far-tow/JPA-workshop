package se.fartow.jpaworkshop.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@EqualsAndHashCode
@ToString(exclude = "book")
@Entity
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;
    @Column(nullable = false)
    private LocalDate dueDate;
    @Column(nullable = false)
    private boolean returned;
    //todo: change to appropriate CascadeType later!!!!
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "appuser_id")
    private AppUser borrower;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "book_id")
    private Book book;

    public BookLoan(LocalDate dueDate, boolean returned, AppUser borrower, Book book) {
        this.dueDate = dueDate;
        this.returned = returned;
        this.borrower = borrower;
        this.book = book;
    }

    public BookLoan() {

    }
}
