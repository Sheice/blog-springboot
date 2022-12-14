package com.sheice.blog.services;

import com.sheice.blog.dtos.PublicationDTO;
import com.sheice.blog.dtos.PublicationResponse;
import com.sheice.blog.entities.Publication;
import com.sheice.blog.exceptions.ResourceNotFoundException;
import com.sheice.blog.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PublicationResponse getAllPublications(int pageNum, int pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));

        Page<Publication> publications = publicationRepository.findAll(pageable);

        List<Publication> listOfPublications = publications.getContent();
        List<PublicationDTO> content =   listOfPublications.stream()
                .map(publication -> mappingDTO(publication)).collect(Collectors.toList());

        PublicationResponse publicationResponse = new PublicationResponse();

        publicationResponse.setContent(content);
        publicationResponse.setPageNum(publications.getNumber());
        publicationResponse.setPageSize(publications.getSize());
        publicationResponse.setTotalElements(publications.getTotalElements());
        publicationResponse.setTotalPages(publications.getTotalPages());
        publicationResponse.setLast(publications.isLast());

        return publicationResponse;

    }

    // GET PUBLICATION BY ID
    @Override
    public PublicationDTO getPublicationById(Long id) {
        Publication publication = publicationRepository.findById(id).
                orElseThrow(
                () -> new ResourceNotFoundException("La publicación", "el id", id)
        );

        return mappingDTO(publication);
    }

    // PUT PUBLICATION BY ID

    @Override
    public PublicationDTO updatePublication(PublicationDTO publicationDTO, Long id) {
        Publication publication = publicationRepository.findById(id).
                orElseThrow(
                        () -> new ResourceNotFoundException("La publicación", "el id", id)
                );

        publication.setTitle(publicationDTO.getTitle());
        publication.setDescription(publicationDTO.getDescription());
        publication.setContent(publicationDTO.getContent());

        Publication publicationUpdated = publicationRepository.save(publication);

        return mappingDTO(publicationUpdated);
    }

    // DELETE PUBLICATION BY ID

    @Override
    public void deletePublication(Long id) {
        Publication publication = publicationRepository.findById(id).
                orElseThrow(
                        () -> new ResourceNotFoundException("La publicación", "el id", id)
                );

        publicationRepository.delete(publication);
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
