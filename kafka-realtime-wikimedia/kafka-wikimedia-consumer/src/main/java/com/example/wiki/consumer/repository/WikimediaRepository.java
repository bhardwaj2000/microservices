package com.example.wiki.consumer.repository;

import com.example.wiki.consumer.WikiMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikimediaRepository extends JpaRepository<WikiMediaEntity, Long> {
}
