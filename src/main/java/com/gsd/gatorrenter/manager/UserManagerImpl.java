package com.gsd.gatorrenter.manager;

import com.gsd.gatorrenter.dto.UserDto;
import com.gsd.gatorrenter.entity.User;
import com.gsd.gatorrenter.utils.exception.GatorRenterException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by Intesar on 3/5/2017.
 */
@Repository
@Transactional
public class UserManagerImpl implements UserManager {

    private static final Logger LOGGER = Logger.getLogger(UserManagerImpl.class);

    @PersistenceContext(unitName = "GatorRenterPU")
    private EntityManager entityManager;

    @Override
    public User findById(Integer userId) {
        try {
            User user = entityManager.find(User.class, userId);

            return user;

        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    @Override
    public UserDto findByEmail(String userEmail) {
        try {
            TypedQuery<User> query = entityManager.createNamedQuery("searchUserByEmail", User.class);
            query.setParameter("userEmail", userEmail.toLowerCase());
            query.setFirstResult(0);
            query.setMaxResults(1);
            User user = query.getSingleResult();

            return user.asDto();
        } catch (NoResultException e) {
            return null;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    @Override
    public UserDto addNewUser(UserDto userDto) throws GatorRenterException {
        try {

            User user = new User(userDto);
            entityManager.persist(user);

            userDto.setPassword(null);
            userDto.setId(user.getId());

            return userDto;

        } catch (Exception ex) {
            throw new GatorRenterException(ex);
        }

    }

}
