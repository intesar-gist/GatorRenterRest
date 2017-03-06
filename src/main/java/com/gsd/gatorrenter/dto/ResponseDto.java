package com.gsd.gatorrenter.dto;

import com.gsd.gatorrenter.utils.EntityHelper;
import com.gsd.gatorrenter.utils.constant.ResponseStatusCode;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Intesar on 3/5/2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GatorRenterResponse")
public class ResponseDto implements Serializable  {

    @XmlElement(name = "apartmentDetails")
    private ApartmentDto apartmentDto;

    @XmlElement(name = "userDetails")
    private UserDto userDto;

    @XmlElement(name = "status")
    private StatusDto statusDto;

    @XmlElement(name = "userTokenDetails")
    UserTokenDto userTokenDto;

    public ApartmentDto getApartmentDto() {
        return apartmentDto;
    }

    public void setApartmentDto(ApartmentDto apartmentDto) {
        this.apartmentDto = apartmentDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
        this.userDto.setPassword(null);
    }

    public StatusDto getStatusDto() {
        return statusDto;
    }

    public void setStatusDto(StatusDto statusDto) {
        this.statusDto = statusDto;
    }

    public UserTokenDto getUserTokenDto() {
        return userTokenDto;
    }

    public void setUserTokenDto(UserTokenDto userTokenDto) {
        this.userTokenDto = userTokenDto;

        if(EntityHelper.isNotNull(this.userTokenDto.getUserDto())) {
            setUserDto(this.userTokenDto.getUserDto());
        }

    }

    public static ResponseDto createSuccessResponse() {
        StatusDto statusDto = new StatusDto(ResponseStatusCode.SUCCESS.getCode(), ResponseStatusCode.SUCCESS.getStatusMessage(), Boolean.TRUE);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatusDto(statusDto);
        return responseDto;
    }

    public static ResponseDto createFailedResponse(String statusCode, String statusMessage) {
        StatusDto statusDto = new StatusDto(Integer.parseInt(statusCode), statusMessage, Boolean.FALSE);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatusDto(statusDto);
        return responseDto;
    }

    public static ResponseDto createFailedResponse(ResponseStatusCode responseStatusCode) {
        return createFailedResponse(responseStatusCode.getCode().toString(), responseStatusCode.getStatusMessage());
    }

    public static Response unauthenticClientResponse() {
        ResponseDto responseDto = ResponseDto.createFailedResponse(ResponseStatusCode.UNAUTHENTICATED_CLIENT);
        return Response.ok().entity(responseDto).build();
    }
}
