package com.example.springmongo.springmongo.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tutorials")
public class Tutorial {
    @Id
    private String id;

    private String title;
    private String description;
    private boolean published;
    private Integer phone;

    public Tutorial(String title, String description, boolean published, Integer phone) {
        this.title = title;
        this.description = description;
        this.published = published;
        this.phone = phone;
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
        return "Tutorial id nya =" + id + ", title nya ini =" + title + ", deskripsinya ini=" + description + ", published=" + published + " phone nya=" + phone +"]";
    }
}