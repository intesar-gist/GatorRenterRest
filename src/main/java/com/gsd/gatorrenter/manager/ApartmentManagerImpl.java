package com.gsd.gatorrenter.manager;

import com.gsd.gatorrenter.dto.ApartmentDto;
import com.gsd.gatorrenter.entity.Apartment;
import com.gsd.gatorrenter.utils.EntityHelper;
import com.gsd.gatorrenter.utils.exception.GatorRenterException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public ApartmentDto getApartmentDtoById(Integer apartmentId) throws Exception {

        Apartment apartment = getApartmentById(apartmentId);
        if(EntityHelper.isNotNull(apartment)) {
            return apartment.asDto(apartment);
        }
        return null;
    }

    @Override
    public Apartment getApartmentById(Integer apartmentId) throws Exception {
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
