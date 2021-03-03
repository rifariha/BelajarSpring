package com.example.springmongo.springmongo.model;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;



@Document(collection = "tutorials")
public class Tutorial {
    @Id
    private String id;

    private String title;
    private String description;
    private boolean published;
    private Integer phone;


    @Field("pegawai_id")
    @Column(name = "pegawai_id")
    private ObjectId pegawai_id;

    public ObjectId getPegawaiId() {
        return pegawai_id;
    }

    public void setPegawaiId(ObjectId pegawai_id) {
        this.pegawai_id = pegawai_id;
    }


    public Tutorial(String title, String description, boolean published, Integer phone, ObjectId pegawai_id) {
        this.title = title;
        this.description = description;
        this.published = published;
        this.phone = phone;
        this.pegawai_id = pegawai_id;
    }

    public String getId() {
        return id;
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

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean isPublished) {
        this.published = isPublished;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer Phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "Tutorial id nya =" + id + ", title nya ini =" + title + ", deskripsinya ini=" + description + ", published=" + published + " phone nya=" + phone + " pegawai id nya=" + pegawai_id + "]";
    }
}