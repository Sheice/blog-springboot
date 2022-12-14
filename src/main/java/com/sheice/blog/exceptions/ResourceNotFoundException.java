package com.sheice.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException  extends RuntimeException{

    private String nameResource;

    private String nameCamp;

    private Long valueCamp;

    // constructor

    public ResourceNotFoundException(String nameResource, String nameCamp, Long valueCamp){
        super(String.format("%s No se encontró con: %s : '%s'", nameResource, nameCamp, valueCamp));

        this.nameResource = nameResource;
        this.nameCamp = nameCamp;
        this.valueCamp = valueCamp;
    }

    // getter and setter


    public String getNameResource() {
        return nameResource;
    }

    public void setNameResource(String nameResource) {
        this.nameResource = nameResource;
    }

    public String getNameCamp() {
        return nameCamp;
    }

    public void setNameCamp(String nameCamp) {
        this.nameCamp = nameCamp;
    }

    public Long getValueCamp() {
        return valueCamp;
    }

    public void setValueCamp(Long valueCamp) {
        this.valueCamp = valueCamp;
    }
}
