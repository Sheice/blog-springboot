package com.sheice.blog.controller;


import com.sheice.blog.dtos.PublicationDTO;
import com.sheice.blog.dtos.PublicationResponse;
import com.sheice.blog.services.PublicationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public PublicationResponse listOfPublications(
            @RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
            ){
        return publicationServices.getAllPublications(pageNum, pageSize);
    }

    // get publication by id
    @GetMapping("/{id}")
    public ResponseEntity<PublicationDTO> getPublicationById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(publicationServices.getPublicationById(id));
    }

    // update publication

    @PutMapping("/{id}")
    public ResponseEntity<PublicationDTO> updatePublication(
            @RequestBody PublicationDTO publicationDTO,
            @PathVariable(name = "id") Long id)
    {
        PublicationDTO publicationResponse = publicationServices.updatePublication(publicationDTO, id);

        return new ResponseEntity<>(publicationResponse, HttpStatus.OK);
    }

    // delete publication

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublication(
            @PathVariable(name = "id") Long id)
    {
        publicationServices.deletePublication(id);

        return new ResponseEntity<>("La publicación fué eliminada", HttpStatus.OK );
    }

}
