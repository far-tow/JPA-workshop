package se.fartow.jpaworkshop.dao;

import se.fartow.jpaworkshop.entity.AppUser;

import java.util.Collection;

public interface AppUserDao {
    AppUser findById(int appUserId);

    Collection<AppUser> findAll();

    AppUser create(AppUser appUser);

    AppUser update(AppUser appUser);

    void delete(int appUserId);
}
