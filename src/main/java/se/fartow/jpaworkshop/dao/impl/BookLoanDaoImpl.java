package se.fartow.jpaworkshop.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.fartow.jpaworkshop.dao.BookLoanDao;
import se.fartow.jpaworkshop.entity.BookLoan;
import se.fartow.jpaworkshop.exceptions.DataNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class BookLoanDaoImpl implements BookLoanDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public BookLoan findById(int id) {
        if (id == 0) throw new IllegalArgumentException("Id can not be null");
        return entityManager.find(BookLoan.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<BookLoan> findAll() {
        return entityManager.createQuery("SELECT b FROM BookLoan b", BookLoan.class).getResultList();
    }

    @Override
    @Transactional
    public BookLoan create(BookLoan bookLoan) {
        if (bookLoan == null) {
            throw new IllegalArgumentException("bookloan was null");
        }
        entityManager.persist(bookLoan);
        return bookLoan;
    }

    @Override
    @Transactional
    public BookLoan update(BookLoan bookLoan) {
        return entityManager.merge(bookLoan);
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            BookLoan bookLoan = entityManager.find(BookLoan.class, id);
            if (bookLoan == null) {
                throw new DataNotFoundException("BookLoan with id " + id + " does not exist");
            }
            entityManager.remove(bookLoan);
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
