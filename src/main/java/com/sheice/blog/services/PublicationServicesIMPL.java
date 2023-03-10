package com.sheice.blog.services;

import com.sheice.blog.dtos.PublicationDTO;
import com.sheice.blog.dtos.PublicationResponse;
import com.sheice.blog.entities.Publication;
import com.sheice.blog.exceptions.ResourceNotFoundException;
import com.sheice.blog.repositories.PublicationRepository;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

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
    public PublicationResponse getAllPublications(int pageNum, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy  ).descending();

        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);

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
                () -> new ResourceNotFoundException("La publicaci??n", "el id", id)
        );

        return mappingDTO(publication);
    }

    // PUT PUBLICATION BY ID

    @Override
    public PublicationDTO updatePublication(PublicationDTO publicationDTO, Long id) {
        Publication publication = publicationRepository.findById(id).
                orElseThrow(
                        () -> new ResourceNotFoundException("La publicaci??n", "el id", id)
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
                        () -> new ResourceNotFoundException("La publicaci??n", "el id", id)
                );

        publicationRepository.delete(publication);
    }

    // CUSTOM METHODS

    /* convert Entity to DTO */
    private PublicationDTO mappingDTO(Publication publication) {
        PublicationDTO publicationDTO = modelMapper.map(publication, PublicationDTO.class);

        return publicationDTO;
    }

    /* convert DTO to Entity */

    private Publication mappingEntity(PublicationDTO publicationDTO){
        Publication publication = modelMapper.map(publicationDTO, Publication.class);

        return publication;
    }
}
