package com.sheice.blog.controller;


import com.sheice.blog.dtos.PublicationDTO;
import com.sheice.blog.services.PublicationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {

    @Autowired
    private PublicationServices publicationServices;

    // create a publication

    @PostMapping
    public ResponseEntity<PublicationDTO> savePublication(@RequestBody PublicationDTO publicationDTO){
            return new ResponseEntity<>(publicationServices.createPublication(publicationDTO), HttpStatus.CREATED);
    }
}
