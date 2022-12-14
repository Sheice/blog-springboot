package com.sheice.blog.services;

import com.sheice.blog.dtos.PublicationDTO;

import java.util.List;

public interface PublicationServices {

    public PublicationDTO createPublication(PublicationDTO publicationDTO);

    public List<PublicationDTO> getAllPublications(int pageNum, int pageSize);

    public PublicationDTO getPublicationById(Long id);

    public PublicationDTO updatePublication(PublicationDTO publicationDTO, Long id);

    public void deletePublication(Long id);

}
