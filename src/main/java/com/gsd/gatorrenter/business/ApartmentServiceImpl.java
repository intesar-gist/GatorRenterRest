package com.gsd.gatorrenter.business;

import com.gsd.gatorrenter.dto.ApartmentDto;
import com.gsd.gatorrenter.dto.ResponseDto;
import com.gsd.gatorrenter.manager.ApartmentManager;
import com.gsd.gatorrenter.utils.EntityHelper;
import com.gsd.gatorrenter.utils.constant.ResponseStatusCode;
import com.gsd.gatorrenter.utils.exception.GatorRenterException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Intesar on 3/6/2017.
 */
@Service
@Transactional
public class ApartmentServiceImpl implements ApartmentService {

    private static final Logger LOGGER = Logger.getLogger(ApartmentServiceImpl.class);

    @Autowired
    private ApartmentManager apartmentManager;

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
}
