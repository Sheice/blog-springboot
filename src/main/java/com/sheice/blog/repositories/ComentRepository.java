package com.sheice.blog.repositories;

import com.sheice.blog.entities.Coment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentRepository extends JpaRepository<Coment, Long> {
    public List<Coment> findByPublicationId(Long publicationId);
}
