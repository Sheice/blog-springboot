package com.sheice.blog.services;

import com.sheice.blog.dtos.ComentDTO;
import com.sheice.blog.entities.Coment;
import com.sheice.blog.entities.Publication;
import com.sheice.blog.exceptions.BlogAppException;
import com.sheice.blog.exceptions.ResourceNotFoundException;
import com.sheice.blog.repositories.ComentRepository;
import com.sheice.blog.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentServicesIMPL implements ComentServices{

    @Autowired
    private ComentRepository comentRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public ComentDTO createComent(long publicationId, ComentDTO comentDTO) {
        Coment coment = mappingEntity(comentDTO);

        Publication publication = publicationRepository.findById(publicationId).
                orElseThrow(
                        () -> new ResourceNotFoundException("La publicación", "el id", publicationId)
                );

        coment.setPublication(publication);

        Coment newComent = comentRepository.save(coment);

        return mappingDTO(newComent);
    }

    @Override
    public List<ComentDTO> getComentByPublicationId(Long publicationId) {
        List<Coment> coments = comentRepository.findByPublicationId(publicationId);

        return coments.stream().map(coment -> mappingDTO(coment)).collect(Collectors.toList());
    }

    @Override
    public ComentDTO getComentById(Long publicationId, Long comentId) {
        Publication publication = publicationRepository.findById(publicationId).
                orElseThrow(
                        () -> new ResourceNotFoundException("La publicación", "el id", publicationId)
                );

        Coment coment = comentRepository.findById(comentId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("El comentario", "el id", comentId)
                );

        if(!coment.getPublication().getId().equals(publication.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
        }

        return mappingDTO(coment);
    }

    // CUSTOM METHODS

    private ComentDTO mappingDTO (Coment coment){
        ComentDTO comentDTO = new ComentDTO();

        comentDTO.setId(coment.getId());
        comentDTO.setName(coment.getName());
        comentDTO.setEmail(coment.getEmail());
        comentDTO.setBody(coment.getBody());

        return comentDTO;
    }

    private Coment mappingEntity(ComentDTO comentDTO){
        Coment coment = new Coment();

        coment.setId(comentDTO.getId());
        coment.setName(comentDTO.getName());
        coment.setEmail(comentDTO.getEmail());
        coment.setBody(comentDTO.getBody());

        return coment;
    }
}
