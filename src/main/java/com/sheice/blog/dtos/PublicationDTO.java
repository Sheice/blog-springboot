package com.sheice.blog.dtos;

import com.sheice.blog.entities.Coment;

import java.util.Set;

public class PublicationDTO {

    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<Coment> coments;
    // constructors


    public PublicationDTO() {
    }

    public PublicationDTO(Long id, String title, String description, String content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
    }


    // getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Coment> getComents() {
        return coments;
    }

    public void setComents(Set<Coment> coments) {
        this.coments = coments;
    }
}
