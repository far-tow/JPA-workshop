package se.fartow.jpaworkshop.entity;

import lombok.*;
import se.fartow.jpaworkshop.exceptions.DataNotFoundException;

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
    @ManyToMany(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id", table = "book_author"),
            inverseJoinColumns = @JoinColumn(name = "author_id", table = "book_author")
    )
    private Set<Author> authors;
    public void addAuthor(Author author){
        if (author != null && authors != null){
            authors.add(author);
        }else {
            try {
                throw new DataNotFoundException("Author  " + author + " does not exist");
            } catch (DataNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void removeAuthor (Author author) {
        if (author != null && authors != null) {
            authors.remove(author);
        } else {
            try {
                throw new DataNotFoundException("Author  " + author + " does not exist");
            } catch (DataNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

   public Book(String isbn, String title, int maxLoanDays) {
       this.isbn = isbn;
       this.title = title;
       this.maxLoanDays = maxLoanDays;
   }

    public Book() {

    }
}
