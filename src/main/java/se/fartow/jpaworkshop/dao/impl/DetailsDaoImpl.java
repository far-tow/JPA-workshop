package se.fartow.jpaworkshop.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.fartow.jpaworkshop.dao.DetailsDao;
import se.fartow.jpaworkshop.entity.Details;
import se.fartow.jpaworkshop.exceptions.DataNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class DetailsDaoImpl implements DetailsDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Details findById(int detailsId) {
        if (detailsId == 0) throw new IllegalArgumentException("Id can not be null");
        return entityManager.find(Details.class, detailsId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Details> findAll() {
        return entityManager.createQuery("SELECT d FROM Details d", Details.class).getResultList();
    }

    @Override
    @Transactional
    public Details create(Details details) {
       if (details == null){
           throw new IllegalArgumentException("Details was null");
       }
        entityManager.persist(details);
        return details;
    }
    @Override
    @Transactional
    public Details update(Details details) {
        return entityManager.merge(details);
    }

    @Override
    @Transactional
    public void delete(int detailsId) {
        try {
            Details details = entityManager.find(Details.class, detailsId);
            if (details == null) {
                throw new DataNotFoundException("Detail with id " + detailsId + " does not exist");
            }
            entityManager.remove(details);
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
