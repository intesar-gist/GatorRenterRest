package com.gsd.gatorrenter.entity;

import javax.persistence.*;

/**
 * Created by Intesar on 3/4/2017.
 */
@Entity
@Table(name = "pictures")
public class Picture {
    private int id;
    private String url;
    private Apartment apartment;

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
    @Column(name = "URL")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ManyToOne
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        if (id != picture.id) return false;
        if (url != null ? !url.equals(picture.url) : picture.url != null) return false;
        return !(apartment != null ? !apartment.equals(picture.apartment) : picture.apartment != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
        return result;
    }
}
