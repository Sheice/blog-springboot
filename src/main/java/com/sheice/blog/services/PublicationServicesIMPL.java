package com.sheice.blog.services;

import com.sheice.blog.dtos.PublicationDTO;
import com.sheice.blog.entities.Publication;
import com.sheice.blog.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationServicesIMPL implements PublicationServices{

    @Autowired
    private PublicationRepository publicationRepository;


    // CREATE PUBLICATION

    @Override
    public PublicationDTO createPublication(PublicationDTO publicationDTO) {

        Publication publication = mappingEntity(publicationDTO);

        Publication newPublication = publicationRepository.save(publication);

        PublicationDTO publicationResponse = mappingDTO(newPublication);


        return publicationResponse;
    }

    // GET ALL PUBLICATION
    @Override
    public List<PublicationDTO> getAllPublications() {
        List<Publication> publications = publicationRepository.findAll();
        return  publications.stream().map(publication -> mappingDTO(publication)).collect(Collectors.toList());
    }

    // CUSTOM METHODS

    /* convert Entity to DTO */
    private PublicationDTO mappingDTO(Publication publication) {
        PublicationDTO publicationDTO = new PublicationDTO();

        publicationDTO.setId(publication.getId());
        publicationDTO.setTitle(publication.getTitle());
        publicationDTO.setDescription(publication.getDescription());
        publicationDTO.setContent(publication.getContent());

        return publicationDTO;
    }

    /* convert DTO to Entity */

    private Publication mappingEntity(PublicationDTO publicationDTO){
        Publication publication = new Publication();

        publication.setTitle(publicationDTO.getTitle());
        publication.setDescription(publicationDTO.getDescription());
        publication.setContent(publicationDTO.getContent());

        return publication;
    }
}
