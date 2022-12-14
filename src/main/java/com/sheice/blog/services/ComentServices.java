package com.sheice.blog.services;

import com.sheice.blog.dtos.ComentDTO;

public interface ComentServices {

    public ComentDTO createComent( long publicationId, ComentDTO comentDTO );
}
