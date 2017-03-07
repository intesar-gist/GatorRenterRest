package com.gsd.gatorrenter.entity;

import com.gsd.gatorrenter.dto.ApartmentDto;
import com.gsd.gatorrenter.utils.DateUtility;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Intesar on 3/3/2017.
 */
@Entity
@Table(name = "apartments")
@NamedQueries({
        @NamedQuery(name = "Apartment.getApartmentsByUserId",
                    query = "select a from Apartment a where a.owner.id = :userId and a.active = true "),
})
@NamedNativeQueries({
        @NamedNativeQuery( name = "Apartment.getApartments",
                           query = "call getApartments(:privateRoom, :privateBath, :kitchenInApartment, :hasSecurityDeposit, :creditScoreCheck, " +
                                   ":userId, :apartmentId, :monthlyRentMin, :monthlyRentMax, :email, null, :pageNumber, :pageSize)",
                           resultClass = Apartment.class)
})
public class Apartment {
    private int id;
    private Boolean active;
    private Date createdAt;
    private Date updatedAt;
    private String state;
    private String addressLine1;
    private String addressLine2;
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
    private Date availableSince;
    private Date leaseEndDate;
    private Double longitude;
    private Boolean flagged;
    private Double latitude;
    private User owner;

    public Apartment() {
    }

    public Apartment(ApartmentDto apartmentDto) {
        this.active = Boolean.TRUE;
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = new Date(System.currentTimeMillis());
        this.state = apartmentDto.getState();
        this.addressLine1 = apartmentDto.getAddressLine1();
        this.city = apartmentDto.getCity();
        this.country = apartmentDto.getCountry();
        this.zip = apartmentDto.getZip();
        this.title = apartmentDto.getTitle();
        this.description = apartmentDto.getDescription();
        this.sqFeet = apartmentDto.getSqFeet();
        this.nrBedrooms = apartmentDto.getNrBedrooms();
        this.nrRoommates = apartmentDto.getNrRoommates();
        this.nrBathrooms = apartmentDto.getNrBathrooms();
        this.floor = apartmentDto.getFloor();
        this.privateRoom = apartmentDto.getPrivateRoom();
        this.privateBath = apartmentDto.getPrivateBath();
        this.kitchenInApartment = apartmentDto.getKitchenInApartment();
        this.hasSecurityDeposit = apartmentDto.getHasSecurityDeposit();
        this.creditScoreCheck = apartmentDto.getCreditScoreCheck();
        this.monthlyRent = apartmentDto.getMonthlyRent();
        this.securityDeposit = apartmentDto.getSecurityDeposit();
        this.availableSince = DateUtility.convertToDate(apartmentDto.getAvailableSince());
        this.leaseEndDate = DateUtility.convertToDate(apartmentDto.getLeaseEndDate());
        this.longitude = apartmentDto.getLongitude();
        this.flagged = apartmentDto.getFlagged();
        this.latitude = apartmentDto.getLatitude();
        this.owner = new User(apartmentDto.getUserDto().getId());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "active")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "address_line_1")
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @Basic
    @Column(name = "address_line_2")
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "zip")
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "sq_feet")
    public Double getSqFeet() {
        return sqFeet;
    }

    public void setSqFeet(Double sqFeet) {
        this.sqFeet = sqFeet;
    }

    @Basic
    @Column(name = "nr_bedrooms")
    public Integer getNrBedrooms() {
        return nrBedrooms;
    }

    public void setNrBedrooms(Integer nrBedrooms) {
        this.nrBedrooms = nrBedrooms;
    }

    @Basic
    @Column(name = "nr_bathrooms")
    public Integer getNrBathrooms() {
        return nrBathrooms;
    }

    public void setNrBathrooms(Integer nrBathrooms) {
        this.nrBathrooms = nrBathrooms;
    }

    @Basic
    @Column(name = "nr_roommates")
    public Integer getNrRoommates() {
        return nrRoommates;
    }

    public void setNrRoommates(Integer nrRoommates) {
        this.nrRoommates = nrRoommates;
    }

    @Basic
    @Column(name = "floor")
    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    @Basic
    @Column(name = "private_room")
    public Boolean getPrivateRoom() {
        return privateRoom;
    }

    public void setPrivateRoom(Boolean privateRoom) {
        this.privateRoom = privateRoom;
    }

    @Basic
    @Column(name = "private_bath")
    public Boolean getPrivateBath() {
        return privateBath;
    }

    public void setPrivateBath(Boolean privateBath) {
        this.privateBath = privateBath;
    }

    @Basic
    @Column(name = "kitchen_in_apartment")
    public Boolean getKitchenInApartment() {
        return kitchenInApartment;
    }

    public void setKitchenInApartment(Boolean kitchenInApartment) {
        this.kitchenInApartment = kitchenInApartment;
    }

    @Basic
    @Column(name = "has_security_deposit")
    public Boolean getHasSecurityDeposit() {
        return hasSecurityDeposit;
    }

    public void setHasSecurityDeposit(Boolean hasSecurityDeposit) {
        this.hasSecurityDeposit = hasSecurityDeposit;
    }

    @Basic
    @Column(name = "credit_score_check")
    public Boolean getCreditScoreCheck() {
        return creditScoreCheck;
    }

    public void setCreditScoreCheck(Boolean creditScoreCheck) {
        this.creditScoreCheck = creditScoreCheck;
    }

    @Basic
    @Column(name = "monthly_rent")
    public Double getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(Double monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    @Basic
    @Column(name = "security_deposit")
    public Double getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(Double securityDeposit) {
        this.securityDeposit = securityDeposit;
    }

    @Basic
    @Column(name = "available_since")
    public Date getAvailableSince() {
        return availableSince;
    }

    public void setAvailableSince(Date availableSince) {
        this.availableSince = availableSince;
    }

    @Basic
    @Column(name = "lease_end_date")
    public Date getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(Date leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    @Basic
    @Column(name = "flagged")
    public Boolean getFlagged() {
        return flagged;
    }

    public void setFlagged(Boolean flagged) {
        this.flagged = flagged;
    }

    @Basic
    @Column(name = "latitude")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "longitude")
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "uid")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public ApartmentDto asDto(Boolean addUserDetails) {
        ApartmentDto apartmentDto = new ApartmentDto(this, addUserDetails);
        return apartmentDto;
    }

    public void updateApartment(ApartmentDto apartmentDto) {
        this.updatedAt = new Date(System.currentTimeMillis());
        this.state = apartmentDto.getState();
        this.addressLine1 = apartmentDto.getAddressLine1();
        this.city = apartmentDto.getCity();
        this.country = apartmentDto.getCountry();
        this.zip = apartmentDto.getZip();
        this.title = apartmentDto.getTitle();
        this.description = apartmentDto.getDescription();
        this.sqFeet = apartmentDto.getSqFeet();
        this.nrBedrooms = apartmentDto.getNrBedrooms();
        this.nrRoommates = apartmentDto.getNrRoommates();
        this.nrBathrooms = apartmentDto.getNrBathrooms();
        this.floor = apartmentDto.getFloor();
        this.privateRoom = apartmentDto.getPrivateRoom();
        this.privateBath = apartmentDto.getPrivateBath();
        this.kitchenInApartment = apartmentDto.getKitchenInApartment();
        this.hasSecurityDeposit = apartmentDto.getHasSecurityDeposit();
        this.creditScoreCheck = apartmentDto.getCreditScoreCheck();
        this.monthlyRent = apartmentDto.getMonthlyRent();
        this.securityDeposit = apartmentDto.getSecurityDeposit();
        this.availableSince = DateUtility.convertToDate(apartmentDto.getAvailableSince());
        this.leaseEndDate = DateUtility.convertToDate(apartmentDto.getLeaseEndDate());
        this.longitude = apartmentDto.getLongitude();
        this.latitude = apartmentDto.getLatitude();
        this.flagged = apartmentDto.getFlagged();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apartment that = (Apartment) o;

        if (id != that.id) return false;
        if (active != null ? !active.equals(that.active) : that.active != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (addressLine1 != null ? !addressLine1.equals(that.addressLine1) : that.addressLine1 != null) return false;
        if (addressLine2 != null ? !addressLine2.equals(that.addressLine2) : that.addressLine2 != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (zip != null ? !zip.equals(that.zip) : that.zip != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (sqFeet != null ? !sqFeet.equals(that.sqFeet) : that.sqFeet != null) return false;
        if (nrBedrooms != null ? !nrBedrooms.equals(that.nrBedrooms) : that.nrBedrooms != null) return false;
        if (nrRoommates != null ? !nrRoommates.equals(that.nrRoommates) : that.nrRoommates != null) return false;
        if (nrBathrooms != null ? !nrBathrooms.equals(that.nrBathrooms) : that.nrBathrooms != null) return false;
        if (floor != null ? !floor.equals(that.floor) : that.floor != null) return false;
        if (privateRoom != null ? !privateRoom.equals(that.privateRoom) : that.privateRoom != null) return false;
        if (privateBath != null ? !privateBath.equals(that.privateBath) : that.privateBath != null) return false;
        if (kitchenInApartment != null ? !kitchenInApartment.equals(that.kitchenInApartment) : that.kitchenInApartment != null)
            return false;
        if (hasSecurityDeposit != null ? !hasSecurityDeposit.equals(that.hasSecurityDeposit) : that.hasSecurityDeposit != null)
            return false;
        if (creditScoreCheck != null ? !creditScoreCheck.equals(that.creditScoreCheck) : that.creditScoreCheck != null)
            return false;
        if (monthlyRent != null ? !monthlyRent.equals(that.monthlyRent) : that.monthlyRent != null) return false;
        if (securityDeposit != null ? !securityDeposit.equals(that.securityDeposit) : that.securityDeposit != null)
            return false;
        if (availableSince != null ? !availableSince.equals(that.availableSince) : that.availableSince != null)
            return false;
        if (leaseEndDate != null ? !leaseEndDate.equals(that.leaseEndDate) : that.leaseEndDate != null) return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;
        if (flagged != null ? !flagged.equals(that.flagged) : that.flagged != null) return false;
        return !(latitude != null ? !latitude.equals(that.latitude) : that.latitude != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (addressLine1 != null ? addressLine1.hashCode() : 0);
        result = 31 * result + (addressLine2 != null ? addressLine2.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (sqFeet != null ? sqFeet.hashCode() : 0);
        result = 31 * result + (nrBedrooms != null ? nrBedrooms.hashCode() : 0);
        result = 31 * result + (nrRoommates != null ? nrRoommates.hashCode() : 0);
        result = 31 * result + (nrBathrooms != null ? nrBathrooms.hashCode() : 0);
        result = 31 * result + (floor != null ? floor.hashCode() : 0);
        result = 31 * result + (privateRoom != null ? privateRoom.hashCode() : 0);
        result = 31 * result + (privateBath != null ? privateBath.hashCode() : 0);
        result = 31 * result + (kitchenInApartment != null ? kitchenInApartment.hashCode() : 0);
        result = 31 * result + (hasSecurityDeposit != null ? hasSecurityDeposit.hashCode() : 0);
        result = 31 * result + (creditScoreCheck != null ? creditScoreCheck.hashCode() : 0);
        result = 31 * result + (monthlyRent != null ? monthlyRent.hashCode() : 0);
        result = 31 * result + (securityDeposit != null ? securityDeposit.hashCode() : 0);
        result = 31 * result + (availableSince != null ? availableSince.hashCode() : 0);
        result = 31 * result + (leaseEndDate != null ? leaseEndDate.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (flagged != null ? flagged.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        return result;
    }
}
