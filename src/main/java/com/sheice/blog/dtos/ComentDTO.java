package com.sheice.blog.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ComentDTO {

    private Long id;

    @NotEmpty(message = "El nombre no debe estar vacío")
    private String name;
    @NotEmpty(message = "El email no debe estar vacío")
    @Email
    private String email;
    @NotEmpty
    @Size(min = 10, message = "El cuerpo del comentario deber tener al menos 10 carácteres")
    private String body;

    // constructors


    public ComentDTO() {
    }

    // getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
