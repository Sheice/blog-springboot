package com.sheice.blog.controller;


import com.sheice.blog.dtos.PublicationDTO;
import com.sheice.blog.services.PublicationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // get all publications

    @GetMapping
    public List<PublicationDTO> listOfPublications(){
        return publicationServices.getAllPublications();
    }

    // get publication by id
    @GetMapping("/{id}")
    public ResponseEntity<PublicationDTO> getPublicationById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(publicationServices.getPublicationById(id));
    }
}
