package com.gsd.gatorrenter.manager;

import com.gsd.gatorrenter.dto.ApartmentDto;
import com.gsd.gatorrenter.entity.Apartment;
import com.gsd.gatorrenter.utils.EntityHelper;
import com.gsd.gatorrenter.utils.exception.GatorRenterException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intesar on 3/4/2017.
 */
@Transactional
@Repository
public class ApartmentManagerImpl implements ApartmentManager {

    private static final Logger LOGGER = Logger.getLogger(ApartmentManagerImpl.class);

    @PersistenceContext(unitName = "GatorRenterPU")
    private EntityManager entityManager;

    @Override
    public ApartmentDto getApartmentDtoById(Integer apartmentId) {

        Apartment apartment = getApartmentById(apartmentId);
        if(EntityHelper.isNotNull(apartment)) {
            return apartment.asDto(false);
        }
        return null;
    }

    @Override
    public Apartment getApartmentById(Integer apartmentId) {
        try {

            Apartment apartment = entityManager.find(Apartment.class, apartmentId);

            if(EntityHelper.isNotNull(apartment)) {
                return apartment;
            }

            return null;

        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    @Override
    public List<ApartmentDto> getApartmentsByUserId(Integer userId) {
        try {
            TypedQuery<Apartment> query = entityManager.createNamedQuery("Apartment.getApartmentsByUserId", Apartment.class);
            query.setParameter("userId", userId);

            List<Apartment> apartments = query.getResultList();
            List<ApartmentDto> apartmentDtos = new ArrayList<>();

            if(EntityHelper.isListPopulated(apartments)) {
                for (Apartment apartment : apartments) {
                    apartmentDtos.add(apartment.asDto(false));
                }
            }

            return apartmentDtos;
        } catch (NoResultException e) {
            return null;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    @Override
    public List<ApartmentDto> filterApartment(Boolean privateRoom, Boolean privateBath, Boolean kitchenInApartment, Boolean hasSecurityDeposit,
                                              Boolean creditScoreCheck, Integer userId, Integer apartmentId, Double monthlyRentMin,
                                              Double monthlyRentMax, String email, Integer pageNumber, Integer pageSize) {
        try {
            TypedQuery<Apartment> query = entityManager.createNamedQuery("Apartment.getApartments", Apartment.class);
            query.setParameter("privateRoom", privateRoom);
            query.setParameter("privateBath", privateBath);
            query.setParameter("kitchenInApartment", kitchenInApartment);
            query.setParameter("hasSecurityDeposit", hasSecurityDeposit);
            query.setParameter("creditScoreCheck", creditScoreCheck);
            query.setParameter("userId", userId);
            query.setParameter("apartmentId", apartmentId);
            query.setParameter("monthlyRentMin", monthlyRentMin);
            query.setParameter("monthlyRentMax", monthlyRentMax);
            query.setParameter("email", EntityHelper.isSet(email) ? email : null);
            query.setParameter("pageNumber", EntityHelper.notNull(pageNumber) ? pageNumber : 1);
            query.setParameter("pageSize", EntityHelper.notNull(pageSize) ? pageSize : 10);

            List<Apartment> apartments = query.getResultList();
            List<ApartmentDto> apartmentDtos = new ArrayList<>();

            if(EntityHelper.isListPopulated(apartments)) {
                for (Apartment apartment : apartments) {
                    apartmentDtos.add(apartment.asDto(false));
                }
            }

            return apartmentDtos;
        } catch (NoResultException e) {
            return null;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    @Override
    public ApartmentDto addNewApartment(ApartmentDto apartmentDto) throws GatorRenterException {
        try {

            Apartment apartment = new Apartment(apartmentDto);
            entityManager.persist(apartment);

            apartmentDto.setId(apartment.getId());

            return apartmentDto;

        } catch (Exception ex) {
            throw new GatorRenterException(ex);
        }

    }

    @Override
    public void updateApartment(Apartment apartment, ApartmentDto apartmentDto) throws GatorRenterException {
        try {

            apartment.updateApartment(apartmentDto);
            entityManager.merge(apartment);

        } catch (Exception ex) {
            throw new GatorRenterException(ex);
        }

    }

}
