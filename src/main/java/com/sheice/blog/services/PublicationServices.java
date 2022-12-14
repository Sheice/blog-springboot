package com.sheice.blog.services;

import com.sheice.blog.dtos.PublicationDTO;
import com.sheice.blog.dtos.PublicationResponse;

import java.util.List;

public interface PublicationServices {

    public PublicationDTO createPublication(PublicationDTO publicationDTO);

    public PublicationResponse getAllPublications(int pageNum, int pageSize, String sortBy);

    public PublicationDTO getPublicationById(Long id);

    public PublicationDTO updatePublication(PublicationDTO publicationDTO, Long id);

    public void deletePublication(Long id);

}
