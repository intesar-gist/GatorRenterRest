package com.gsd.gatorrenter.manager;

import com.gsd.gatorrenter.dto.ApartmentDto;
import com.gsd.gatorrenter.entity.Apartment;
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
    public ApartmentDto getApartmentById(Integer apartmentId) throws Exception {
        Apartment apartment = entityManager.find(Apartment.class, apartmentId);
        return apartment.asDto(apartment);
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

}
