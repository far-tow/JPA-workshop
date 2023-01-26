package se.fartow.jpaworkshop.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.fartow.jpaworkshop.dao.AuthorDao;
import se.fartow.jpaworkshop.entity.Author;
import se.fartow.jpaworkshop.exceptions.DataNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Author findById(int id) {
        if (id == 0) throw new IllegalArgumentException("Id can not be null");
        return entityManager.find(Author.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Author> findAll() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }

    @Override
    @Transactional
    public Author create(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("Author was null");
        }
        entityManager.persist(author);
        return author;
    }

    @Override
    @Transactional
    public Author update(Author id) {
        return entityManager.merge(id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            Author author = entityManager.find(Author.class, id);
            if (author == null) {
                throw new DataNotFoundException("Author with id " + id + " does not exist");
            }
            entityManager.remove(author);
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
