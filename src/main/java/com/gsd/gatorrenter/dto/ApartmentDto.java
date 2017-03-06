package com.gsd.gatorrenter.dto;

import com.gsd.gatorrenter.entity.Apartment;
import com.gsd.gatorrenter.utils.EntityHelper;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Intesar on 3/4/2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "apartmentDetails")
public class ApartmentDto implements Serializable {
    private int id;
    private Boolean active;
    private String createdAt;

    @XmlTransient
    private String updatedAt;

    private String state;
    private String addressLine1;
    private String city;
    private String country;
    private String zip;
    private String title;
    private String description;
    private Double sqFeet;
    private Integer nrBedrooms;
    private Integer nrRoommates;
    private Integer nrBathrooms;
    private Integer floor;
    private Boolean privateRoom;
    private Boolean privateBath;
    private Boolean kitchenInApartment;
    private Boolean hasSecurityDeposit;
    private Boolean creditScoreCheck;
    private Double monthlyRent;
    private Double securityDeposit;
    private String availableSince;
    private String leaseEndDate;
    private Double longitude;
    private Boolean flagged;
    private Double latitude;

    @XmlElement(name = "owner")
    private UserDto userDto;

    public ApartmentDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSqFeet() {
        return sqFeet;
    }

    public void setSqFeet(Double sqFeet) {
        this.sqFeet = sqFeet;
    }

    public Integer getNrBedrooms() {
        return nrBedrooms;
    }

    public void setNrBedrooms(Integer nrBedrooms) {
        this.nrBedrooms = nrBedrooms;
    }

    public Integer getNrRoommates() {
        return nrRoommates;
    }

    public void setNrRoommates(Integer nrRoommates) {
        this.nrRoommates = nrRoommates;
    }

    public Integer getNrBathrooms() {
        return nrBathrooms;
    }

    public void setNrBathrooms(Integer nrBathrooms) {
        this.nrBathrooms = nrBathrooms;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Boolean getPrivateRoom() {
        return privateRoom;
    }

    public void setPrivateRoom(Boolean privateRoom) {
        this.privateRoom = privateRoom;
    }

    public Boolean getPrivateBath() {
        return privateBath;
    }

    public void setPrivateBath(Boolean privateBath) {
        this.privateBath = privateBath;
    }

    public Boolean getKitchenInApartment() {
        return kitchenInApartment;
    }

    public void setKitchenInApartment(Boolean kitchenInApartment) {
        this.kitchenInApartment = kitchenInApartment;
    }

    public Boolean getHasSecurityDeposit() {
        return hasSecurityDeposit;
    }

    public void setHasSecurityDeposit(Boolean hasSecurityDeposit) {
        this.hasSecurityDeposit = hasSecurityDeposit;
    }

    public Boolean getCreditScoreCheck() {
        return creditScoreCheck;
    }

    public void setCreditScoreCheck(Boolean creditScoreCheck) {
        this.creditScoreCheck = creditScoreCheck;
    }

    public Double getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(Double monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public Double getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(Double securityDeposit) {
        this.securityDeposit = securityDeposit;
    }

    public String getAvailableSince() {
        return availableSince;
    }

    public void setAvailableSince(String availableSince) {
        this.availableSince = availableSince;
    }

    public String getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(String leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Boolean getFlagged() {
        return flagged;
    }

    public void setFlagged(Boolean flagged) {
        this.flagged = flagged;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public ApartmentDto(Apartment apartmentEntity) {
        this.id = apartmentEntity.getId();
        this.active = apartmentEntity.getActive();
        this.createdAt = apartmentEntity.getCreatedAt().toString();
        this.updatedAt = apartmentEntity.getUpdatedAt().toString();
        this.state = apartmentEntity.getState();
        this.addressLine1 = apartmentEntity.getAddressLine1();
        this.city = apartmentEntity.getCity();
        this.country = apartmentEntity.getCountry();
        this.zip = apartmentEntity.getZip();
        this.title = apartmentEntity.getTitle();
        this.description = apartmentEntity.getDescription();
        this.sqFeet = apartmentEntity.getSqFeet();
        this.nrBedrooms = apartmentEntity.getNrBedrooms();
        this.nrRoommates = apartmentEntity.getNrRoommates();
        this.nrBathrooms = apartmentEntity.getNrBathrooms();
        this.floor = apartmentEntity.getFloor();
        this.privateRoom = apartmentEntity.getPrivateRoom();
        this.privateBath = apartmentEntity.getPrivateBath();
        this.kitchenInApartment = apartmentEntity.getKitchenInApartment();
        this.hasSecurityDeposit = apartmentEntity.getHasSecurityDeposit();
        this.creditScoreCheck = apartmentEntity.getCreditScoreCheck();
        this.monthlyRent = apartmentEntity.getMonthlyRent();
        this.securityDeposit = apartmentEntity.getSecurityDeposit();
        this.availableSince = apartmentEntity.getAvailableSince().toString();
        this.leaseEndDate = apartmentEntity.getLeaseEndDate().toString();
        this.longitude = apartmentEntity.getLongitude();
        this.flagged = apartmentEntity.getFlagged();
        this.latitude = apartmentEntity.getLatitude();

        if(EntityHelper.isNotNull(apartmentEntity.getOwner())) {
            this.userDto = apartmentEntity.getOwner().asDto();
        }
    }
}
