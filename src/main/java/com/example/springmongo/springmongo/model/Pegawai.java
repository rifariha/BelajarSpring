package com.example.springmongo.springmongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Document(collection = "pegawai")
public class Pegawai  {
    @Id
    private String id;

    @NotEmpty(message = "First name is mandatory")
//    @Column(name="first_name")
    @Field("first_name")
    private String firstName;

    @NotEmpty(message = "last name must not be empty")
//    @Column(name="first_name")
    @Field("last_name")
    private String lastName;


    private String nip;
    @Field("photo")
    private String photo;

    public Pegawai(String first_name, String last_name, String nip, String photo) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.nip = nip;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }



}
