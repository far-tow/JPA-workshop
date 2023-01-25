package se.fartow.jpaworkshop.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.fartow.jpaworkshop.dao.BookDao;
import se.fartow.jpaworkshop.entity.Book;
import se.fartow.jpaworkshop.entity.Details;
import se.fartow.jpaworkshop.exceptions.DataNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Book findById(int id) {
        if (id == 0) throw new IllegalArgumentException("Id can not be null");
        return entityManager.find(Book.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Override
    @Transactional
    public Book create(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book was null");
        }
        entityManager.persist(book);
        return book;
    }

    @Override
    @Transactional
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            Book book = entityManager.find(Book.class, id);
            if (book == null) {
                throw new DataNotFoundException("Book with id " + id + " does not exist");
            }
            entityManager.remove(book);
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

