package com.gsd.gatorrenter;

import com.gsd.gatorrenter.business.ApartmentService;
import com.gsd.gatorrenter.business.ApartmentServiceImpl;
import com.gsd.gatorrenter.dto.ApartmentDto;
import com.gsd.gatorrenter.dto.ResponseDto;
import com.gsd.gatorrenter.dto.UserDto;
import com.gsd.gatorrenter.entity.Apartment;
import com.gsd.gatorrenter.manager.ApartmentManager;
import com.gsd.gatorrenter.manager.UserManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class AdvertisementModuleTests {

    @InjectMocks
    ApartmentService apartmentService = new ApartmentServiceImpl();

    @Mock //@Mock annotation is used to create the mock object to be injected
    UserManager userManager;

    @Mock
    ApartmentManager apartmentManager;

    @Before
    public void init() {

    }

    /***********************
     * ADD APARTMENT TESTS
     * *********************
     */
    @Test
    public void test_addNewApartment_ShouldFail_ownerIdIsNull() throws Exception {
        ApartmentDto apartmentDto = new ApartmentDto(1, "This is new apartment", "this is description", null);

        ResponseDto responseDto = apartmentService.addNewApartment(apartmentDto);

        Assert.assertFalse("Apartment added successfully, should not have done coz no owner id was passed", responseDto.getStatusDto().getSuccess());
        Assert.assertTrue("Response code is not 9, something else happen", 9 == responseDto.getStatusDto().getStatusCode());
    }

    @Test
    public void test_addNewApartment_ShouldFail_basicDetailsAreNull() throws Exception {
        ApartmentDto apartmentDto = new ApartmentDto(1, null, null, new UserDto(12));
        ResponseDto responseDto = apartmentService.addNewApartment(apartmentDto);

        Assert.assertFalse("Apartment added successfully, should not have done coz empty details were passed", responseDto.getStatusDto().getSuccess());
        Assert.assertTrue("Response code is not 10, something else happen", 10 == responseDto.getStatusDto().getStatusCode());
    }

    @Test
    public void test_addNewApartment_ShouldPass_allRequiredDetailGiven() throws Exception {
        ApartmentDto requestApartment = new ApartmentDto(13, "Title", "Description", new UserDto(12));
        ApartmentDto responseApartment = new ApartmentDto(183, "Title", "Description", new UserDto(12));

        Mockito
                .when(apartmentManager.addNewApartment(requestApartment))
                .thenReturn(responseApartment);

        ResponseDto responseDto = apartmentService.addNewApartment(requestApartment);

        Assert.assertTrue("Apartment did not add successfully", responseDto.getStatusDto().getSuccess());
        Assert.assertTrue("Apartment ID is not 183, apartment couldn't be added properly", responseDto.getApartmentDto().getId() == 183);
    }

    /***********************
     * Update APARTMENT TESTS
     * *********************
     */
    @Test
    public void test_updateApartment_ShouldFail_noApartmentFoundAgainstGivenAptID() throws Exception {
        ApartmentDto apartmentDto = new ApartmentDto(12, "This is new apartment", "this is description", null);

        Mockito
                .when(apartmentManager.getApartmentById(12))
                .thenReturn(null);

        ResponseDto responseDto = apartmentService.updateApartment(apartmentDto);

        Assert.assertFalse("Apartment updated successfully, should not have done coz wrong apartment id was passed", responseDto.getStatusDto().getSuccess());
        Assert.assertTrue("Response code is not 12, something else happen", 12 == responseDto.getStatusDto().getStatusCode());
    }

    @Test
    public void test_updateApartment_ShouldFail_unknownExceptionWhileSavingInDB() throws Exception {
        ApartmentDto apartmentDto = new ApartmentDto(12, "This is new apartment", "this is description", null);

        ResponseDto responseDto = apartmentService.updateApartment(apartmentDto);

        Assert.assertFalse("Apartment updated successfully, should not have done coz db went down", responseDto.getStatusDto().getSuccess());
        Assert.assertTrue("Response code is not -1, something else happen", -1 == responseDto.getStatusDto().getStatusCode());
    }
}
