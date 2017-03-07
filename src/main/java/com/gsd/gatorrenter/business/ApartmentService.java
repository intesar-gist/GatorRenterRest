package com.gsd.gatorrenter.business;

import com.gsd.gatorrenter.dto.ApartmentDto;
import com.gsd.gatorrenter.dto.ResponseDto;

/**
 * Created by Intesar on 3/6/2017.
 */
public interface ApartmentService {

    ResponseDto addNewApartment(ApartmentDto apartmentDto);
    ResponseDto updateApartment(ApartmentDto apartmentDto);
    ResponseDto getApartmentsByUserId(Integer userId);
    ResponseDto getApartmentById(Integer apartmentId);
    ResponseDto filterApartment( Boolean privateRoom, Boolean privateBath, Boolean kitchenInApartment, Boolean hasSecurityDeposit,
                                 Boolean creditScoreCheck, Integer userId, Integer apartmentId, Double monthlyRentMin,
                                 Double monthlyRentMax, String email, Integer pageNumber, Integer pageSize);

}
