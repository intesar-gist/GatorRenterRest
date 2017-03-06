package com.gsd.gatorrenter.business;

import com.gsd.gatorrenter.dto.ApartmentDto;
import com.gsd.gatorrenter.dto.ResponseDto;
import com.gsd.gatorrenter.dto.UserDto;
import com.gsd.gatorrenter.entity.Apartment;
import com.gsd.gatorrenter.entity.User;
import com.gsd.gatorrenter.manager.ApartmentManager;
import com.gsd.gatorrenter.manager.UserManager;
import com.gsd.gatorrenter.utils.EntityHelper;
import com.gsd.gatorrenter.utils.constant.ResponseStatusCode;
import com.gsd.gatorrenter.utils.exception.GatorRenterException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Intesar on 3/6/2017.
 */
@Service
@Transactional
public class ApartmentServiceImpl implements ApartmentService {

    private static final Logger LOGGER = Logger.getLogger(ApartmentServiceImpl.class);

    @Autowired
    private ApartmentManager apartmentManager;

    @Autowired
    private UserManager userManager;

    @Override
    public ResponseDto addNewApartment(ApartmentDto apartmentDto) {

        try {

            if(EntityHelper.isNull(apartmentDto.getUserDto()) || EntityHelper.isNull(apartmentDto.getUserDto().getId())) {
                throw new GatorRenterException(ResponseStatusCode.MISSING_OWNER_USER_ID);
            }

            if(EntityHelper.allEmpty(apartmentDto.getAddressLine1(), apartmentDto.getAvailableSince(), apartmentDto.getCity(), apartmentDto.getCountry(),
                    apartmentDto.getTitle(), apartmentDto.getDescription(), apartmentDto.getState(), apartmentDto.getLeaseEndDate())) {
                throw new GatorRenterException(ResponseStatusCode.APT_DETAILS_MISSING);
            }

            apartmentManager.addNewApartment(apartmentDto);

            ResponseDto responseDto = ResponseDto.createSuccessResponse();
            responseDto.setApartmentDto(apartmentDto);

            return responseDto;

        } catch (GatorRenterException ex) {
            LOGGER.error(ex);
            return ResponseDto.createFailedResponse(ex.getCode(), ex.getMessage());
        } catch (Exception ex) {
            LOGGER.error(ex);
            return ResponseDto.createFailedResponse(ResponseStatusCode.SOMETHING_UNEXPECTED_HAPPENED);
        }
    }

    @Override
    public ResponseDto updateApartment(ApartmentDto apartmentDto) {

        try {

            if(!EntityHelper.isSet(apartmentDto.getId())) {
                throw new GatorRenterException(ResponseStatusCode.MISSING_APARTMENT_ID);
            }

            Apartment apartment = apartmentManager.getApartmentById(apartmentDto.getId());

            if(EntityHelper.isNull(apartment)) {
                throw new GatorRenterException(ResponseStatusCode.APARTMENT_NOT_FOUND, apartmentDto.getId());
            }

            if(EntityHelper.allEmpty(apartmentDto.getAddressLine1(), apartmentDto.getAvailableSince(), apartmentDto.getCity(), apartmentDto.getCountry(),
                    apartmentDto.getTitle(), apartmentDto.getDescription(), apartmentDto.getState(), apartmentDto.getLeaseEndDate())) {
                throw new GatorRenterException(ResponseStatusCode.APT_DETAILS_MISSING);
            }

            apartmentManager.updateApartment(apartment, apartmentDto);

            return ResponseDto.createSuccessResponse();

        } catch (GatorRenterException ex) {
            LOGGER.error(ex);
            return ResponseDto.createFailedResponse(ex.getCode(), ex.getMessage());
        } catch (Exception ex) {
            LOGGER.error(ex);
            return ResponseDto.createFailedResponse(ResponseStatusCode.SOMETHING_UNEXPECTED_HAPPENED);
        }
    }

    @Override
    public ResponseDto getApartmentsByUserId(Integer userId) {

        try {

            UserDto userDto = userManager.findUserDtoById(userId);

            if(EntityHelper.isNull(userDto)) {
                throw new GatorRenterException(ResponseStatusCode.USER_NOT_FOUND, userId);
            }

            List<ApartmentDto> apartments = apartmentManager.getApartmentsByUserId(userDto.getId());

            ResponseDto responseDto = ResponseDto.createSuccessResponse();
            responseDto.setApartmentsList(apartments);

            return responseDto;

        } catch (GatorRenterException ex) {
            LOGGER.error(ex);
            return ResponseDto.createFailedResponse(ex.getCode(), ex.getMessage());
        } catch (Exception ex) {
            LOGGER.error(ex);
            return ResponseDto.createFailedResponse(ResponseStatusCode.SOMETHING_UNEXPECTED_HAPPENED);
        }
    }
}
