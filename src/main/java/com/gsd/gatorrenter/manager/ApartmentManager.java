package com.gsd.gatorrenter.manager;

import com.gsd.gatorrenter.dto.ApartmentDto;
import com.gsd.gatorrenter.entity.Apartment;
import com.gsd.gatorrenter.utils.exception.GatorRenterException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Intesar on 3/4/2017.
 */
@Transactional
public interface ApartmentManager {

    ApartmentDto getApartmentDtoById(Integer apartmentId) throws Exception;
    Apartment getApartmentById(Integer apartmentId) throws Exception;
    ApartmentDto addNewApartment(ApartmentDto apartmentDto) throws GatorRenterException;
    void updateApartment(Apartment apartment, ApartmentDto apartmentDto) throws GatorRenterException;

}
