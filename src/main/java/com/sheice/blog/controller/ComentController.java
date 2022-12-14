package com.sheice.blog.controller;

import com.sheice.blog.dtos.ComentDTO;
import com.sheice.blog.services.ComentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ComentController {

    @Autowired
    ComentServices comentServices;

    @PostMapping("/publicacion/{publicationId}/comentarios")
    public ResponseEntity<ComentDTO> saveComent(@PathVariable(value = "publicationId") Long publicationId,
    @RequestBody ComentDTO comentDTO){
        return new ResponseEntity<>(comentServices.createComent(publicationId, comentDTO), HttpStatus.CREATED);
    }
}
