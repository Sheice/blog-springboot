package com.sheice.blog.controller;

import com.sheice.blog.dtos.ComentDTO;
import com.sheice.blog.services.ComentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/publicacion/{publicationId}/comentarios")
    List<ComentDTO> listOfComentsByPublications(@PathVariable(value = "publicationId") Long publicationId){
        return comentServices.getComentByPublicationId(publicationId);
    }

    @GetMapping("/publicacion/{publicationId}/comentario/{comentId}")
    public ResponseEntity<ComentDTO> getComentById(
            @PathVariable(value = "publicationId") Long publicationId,
            @PathVariable(value = "comentId") Long comentId
    ){
        ComentDTO comentDTO = comentServices.getComentById(publicationId, comentId);

        return new ResponseEntity<>(comentDTO, HttpStatus.OK);
    }

    @PutMapping("/publicacion/{publicationId}/comentario/{comentId}")
    public ResponseEntity<ComentDTO> editComent(
            @PathVariable(value = "publicationId") Long publicationId,
            @PathVariable(value = "comentId") Long comentId,
            @RequestBody ComentDTO comentDTO
    ){
        ComentDTO comentEdited = comentServices.updateComent(publicationId,comentId,comentDTO);

        return new ResponseEntity<>(comentEdited, HttpStatus.OK);
    }

    @DeleteMapping("/publicacion/{publicationId}/comentario/{comentId}")
    public ResponseEntity<String> deleteComent(
            @PathVariable(value = "publicationId") Long publicationId,
            @PathVariable(value = "comentId") Long comentId
    ){
        comentServices.deleteComent(publicationId,comentId);

        return new ResponseEntity<>("Comentario eliminado correctamente", HttpStatus.OK);
    }
}
