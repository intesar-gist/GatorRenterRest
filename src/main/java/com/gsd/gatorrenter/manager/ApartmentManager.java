package com.gsd.gatorrenter.manager;

import com.gsd.gatorrenter.dto.ApartmentDto;
import com.gsd.gatorrenter.entity.Apartment;
import com.gsd.gatorrenter.utils.exception.GatorRenterException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Intesar on 3/4/2017.
 */
@Transactional
public interface ApartmentManager {

    ApartmentDto getApartmentDtoById(Integer apartmentId);
    Apartment getApartmentById(Integer apartmentId);
    List<ApartmentDto> getApartmentsByUserId(Integer userId);
    List<ApartmentDto> filterApartment(Boolean privateRoom, Boolean privateBath, Boolean kitchenInApartment, Boolean hasSecurityDeposit,
                                       Boolean creditScoreCheck, Integer userId, Integer apartmentId, Double monthlyRentMin,
                                       Double monthlyRentMax, String email, Integer pageNumber, Integer pageSize);
    ApartmentDto addNewApartment(ApartmentDto apartmentDto) throws GatorRenterException;
    void updateApartment(Apartment apartment, ApartmentDto apartmentDto) throws GatorRenterException;

}
