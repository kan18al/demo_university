package com.example.demo.service;

import com.example.demo.model.Audience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudienceRepository extends JpaRepository<Audience, Integer> {
}
