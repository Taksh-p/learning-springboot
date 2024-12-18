package com.example.SpringSecurity.SpringSecurityApplication.repository;

import com.example.SpringSecurity.SpringSecurityApplication.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
