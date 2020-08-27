package com.lysachenko.repository;

import com.lysachenko.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserRepository {

    private static final String GET_ALL_USERS = "select u from User u";
    private static final String GET_USER_BY_ID = "select u from User u where u.id = ?1";
    private static final String GET_USER_BY_NAME = "select u from User u where u.name = ?1";

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void save(User user) {
        getCurrentSession().save(user);
    }

    @Transactional
    public void update(User user) {
        getCurrentSession().update(user);
    }

    @Transactional
    public void delete(User user) {
        getCurrentSession().delete(user);
    }

    @Transactional
    public List<User> getAll() {
        return getCurrentSession().createQuery(GET_ALL_USERS, User.class).getResultList();
    }

    @Transactional
    public User getUserById(Long id) {
        try {
            return getCurrentSession()
                    .createQuery(GET_USER_BY_ID, User.class)
                    .setParameter(1, id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public List<User> getUserByName(String name) {
        return getCurrentSession()
                .createQuery(GET_USER_BY_NAME, User.class)
                .setParameter(1, name)
                .getResultList();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
