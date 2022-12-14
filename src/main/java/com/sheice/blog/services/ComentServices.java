package com.sheice.blog.services;

import com.sheice.blog.dtos.ComentDTO;

import java.util.List;

public interface ComentServices {

    public ComentDTO createComent( long publicationId, ComentDTO comentDTO );

    public List<ComentDTO> getComentByPublicationId(Long publicationId);

    public ComentDTO getComentById(Long publicationId, Long comentId);
}
