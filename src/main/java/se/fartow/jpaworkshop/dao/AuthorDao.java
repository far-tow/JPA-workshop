package se.fartow.jpaworkshop.dao;
import se.fartow.jpaworkshop.entity.Author;

import java.util.Collection;

public interface AuthorDao {
    Author findById (int id);
    Collection<Author>findAll();
    Author create(Author author);
    Author update (Author id);
    void delete (int id);
}
