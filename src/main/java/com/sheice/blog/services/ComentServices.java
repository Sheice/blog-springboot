package com.sheice.blog.services;

import com.sheice.blog.dtos.ComentDTO;

import java.util.List;

public interface ComentServices {

    public ComentDTO createComent( long publicationId, ComentDTO comentDTO );

    public List<ComentDTO> getComentByPublicationId(Long publicationId);

    public ComentDTO getComentById(Long publicationId, Long comentId);

    public ComentDTO updateComent(Long publicationId,Long comentId, ComentDTO requestOfComent);

    public void deleteComent(Long publicationId,Long comentId);
}
