package com.gsd.gatorrenter.manager;

import com.gsd.gatorrenter.dto.UserDto;
import com.gsd.gatorrenter.dto.UserTokenDto;
import com.gsd.gatorrenter.entity.User;
import com.gsd.gatorrenter.entity.UserToken;
import com.gsd.gatorrenter.utils.EntityHelper;
import com.gsd.gatorrenter.utils.RandomGenerator;
import com.gsd.gatorrenter.utils.constant.ResponseStatusCode;
import com.gsd.gatorrenter.utils.exception.GatorRenterException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * Created by Intesar on 3/5/2017.
 */
@Repository
@Transactional
public class UserTokenManagerImpl implements UserTokenManager {

    private static final Logger LOGGER = Logger.getLogger(UserTokenManagerImpl.class);

    @PersistenceContext(unitName = "GatorRenterPU")
    private EntityManager entityManager;

    @Autowired
    private UserManager userManager;

    @Override
    public UserTokenDto findByAccessTokenAndUserId(Integer userId, String accessToken) throws GatorRenterException {
        TypedQuery<UserToken> query = entityManager.createNamedQuery("UserToken.searchByAccessTokenUserId", UserToken.class);
        query.setParameter("accessToken", accessToken);
        query.setParameter("userId", userId);
        query.setFirstResult(0);
        query.setMaxResults(1);
        try {
            UserToken userToken = query.getSingleResult();
            return userToken.asDto();
        } catch (NoResultException e) {
            return null;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new GatorRenterException(ResponseStatusCode.UNAUTHENTICATED_CLIENT);
        }
    }


    @Override
    public UserTokenDto addToken(UserDto userDto) throws GatorRenterException {
        try {

            //remove already existing tokens first
            removeUserTokens(userDto);

            User user = userManager.findById(userDto.getId());

            UserToken userToken = new UserToken(RandomGenerator.generateUid(), 1,
                    new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()),
                    user);
            entityManager.persist(userToken);

            return userToken.asDto();

        } catch (Exception ex) {
            throw new GatorRenterException(ex);
        }
    }


    @Override
    public void removeUserTokens(UserDto userDto) throws GatorRenterException {

        try {
            TypedQuery<UserToken> query = entityManager.createNamedQuery("UserToken.getUserTokensByUserId", UserToken.class);
            query.setParameter("userId", userDto.getId());

            List<UserToken> userTokens = query.getResultList();

            if(EntityHelper.isListPopulated(userTokens)) {
                for (UserToken userToken : userTokens) {
                    entityManager.remove(userToken);
                }
            }

        }catch (Exception ex) {
            LOGGER.error(ex);
            throw new GatorRenterException(ResponseStatusCode.SOMETHING_UNEXPECTED_HAPPENED, ex.getMessage());
        }

    }

}
