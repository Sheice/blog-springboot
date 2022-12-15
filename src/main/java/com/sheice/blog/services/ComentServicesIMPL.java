package com.sheice.blog.services;

import com.sheice.blog.dtos.ComentDTO;
import com.sheice.blog.entities.Coment;
import com.sheice.blog.entities.Publication;
import com.sheice.blog.exceptions.BlogAppException;
import com.sheice.blog.exceptions.ResourceNotFoundException;
import com.sheice.blog.repositories.ComentRepository;
import com.sheice.blog.repositories.PublicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentServicesIMPL implements ComentServices{

    @Autowired
    private ModelMapper modelMapper;

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

    @Override
    public ComentDTO updateComent(Long publicationId, Long comentId, ComentDTO requestOfComent) {
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
        coment.setName(requestOfComent.getName());
        coment.setEmail(requestOfComent.getEmail());
        coment.setBody(requestOfComent.getBody());

        Coment comentUpdated = comentRepository.save(coment);

        return mappingDTO(comentUpdated);
    }

    @Override
    public void deleteComent(Long publicationId, Long comentId) {
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

        comentRepository.delete(coment);
    }

    // CUSTOM METHODS

    private ComentDTO mappingDTO (Coment coment){
        ComentDTO comentDTO = modelMapper.map(coment, ComentDTO.class);

        return comentDTO;
    }

    private Coment mappingEntity(ComentDTO comentDTO){
        Coment coment = modelMapper.map(comentDTO, Coment.class);

        return coment;
    }
}
