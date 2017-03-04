package com.gsd.gatorrenter.manager;

import com.gsd.gatorrenter.dto.ApartmentDto;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Intesar on 3/4/2017.
 */
@Transactional
public interface ApartmentManager {

    ApartmentDto getApartmentById(Integer apartmentId) throws Exception;

}
