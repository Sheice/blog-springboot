package com.sheice.blog.services;

import com.sheice.blog.dtos.PublicationDTO;
import com.sheice.blog.entities.Publication;
import com.sheice.blog.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationServicesIMPL implements PublicationServices{

    @Autowired
    private PublicationRepository publicationRepository;


    @Override
    public PublicationDTO createPublication(PublicationDTO publicationDTO) {

        // convert DTO to Entity

        Publication publication = new Publication();

        publication.setTitle(publicationDTO.getTitle());
        publication.setDescription(publicationDTO.getDescription());
        publication.setContent(publicationDTO.getContent());

        Publication newPublication = publicationRepository.save(publication);

        // convert Entity to DTO

        PublicationDTO publicationResponse = new PublicationDTO();

        publicationResponse.setId(newPublication.getId());
        publicationResponse.setTitle(newPublication.getTitle());
        publicationResponse.setDescription(newPublication.getDescription());
        publicationResponse.setContent(newPublication.getContent());


        return publicationResponse;
    }
}
