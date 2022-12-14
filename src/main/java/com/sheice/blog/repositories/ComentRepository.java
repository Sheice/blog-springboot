package com.sheice.blog.repositories;

import com.sheice.blog.entities.Coment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentRepository extends JpaRepository<Coment, Long> {
}
