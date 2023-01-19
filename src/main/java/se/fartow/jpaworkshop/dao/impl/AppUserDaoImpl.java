package se.fartow.jpaworkshop.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.fartow.jpaworkshop.dao.AppUserDao;
import se.fartow.jpaworkshop.entity.AppUser;
import se.fartow.jpaworkshop.exceptions.DataNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class AppUserDaoImpl implements AppUserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public AppUser findById(int appUserId) {
        if (appUserId == 0) throw new IllegalArgumentException("Id can not be null");
        return entityManager.find(AppUser.class, appUserId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<AppUser> findAll() {
        return entityManager.createQuery("SELECT a FROM AppUser a", AppUser.class).getResultList();
    }

    @Override
    @Transactional
    public AppUser create(AppUser appUser) {
        if (appUser == null) throw new IllegalArgumentException("AppUser was null");
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    @Transactional
    public AppUser update(AppUser appUser) {
        return entityManager.merge(appUser);
    }

    @Override
    @Transactional
    public void delete(int appUserId) {
        AppUser appUser = entityManager.find(AppUser.class, appUserId);
        if (appUser != null) entityManager.remove(appUser);
        else try {
            throw new DataNotFoundException("AppUser id dosn't exist!");
        }catch (DataNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
