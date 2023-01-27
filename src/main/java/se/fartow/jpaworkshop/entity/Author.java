package se.fartow.jpaworkshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import se.fartow.jpaworkshop.exceptions.DataNotFoundException;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode
@ToString
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column
    @ManyToMany(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "authors"
    )
    private Set<Book> writtenBooks;
    public void addBook(Book book){
        if (book != null && writtenBooks != null){
            writtenBooks.add(book);
        }else {
            try {
                throw new DataNotFoundException("Book  " + book + " does not exist");
            } catch (DataNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void removeBook (Book book) {
        if (book != null && writtenBooks != null) {
            writtenBooks.remove(book);
        } else {
            try {
                throw new DataNotFoundException("Book  " + book + " does not exist");
            } catch (DataNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Author() {
    }

    public Author(String firstName, String lastName, Set<Book> writtenBooks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.writtenBooks = writtenBooks;
    }
}
