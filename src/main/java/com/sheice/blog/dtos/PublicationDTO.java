package com.sheice.blog.dtos;

import com.sheice.blog.entities.Coment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class PublicationDTO {

    private Long id;
    @NotEmpty
    @Size(min = 2, message = "El título de la publicación debería tener al menos 2 carácteres")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "La descripción de la publicación debería tener al menos 10 carácteres")
    private String description;
    @NotEmpty
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
