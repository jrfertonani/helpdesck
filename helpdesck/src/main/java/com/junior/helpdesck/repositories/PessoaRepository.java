package com.junior.helpdesck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Pessoa extends JpaRepository<Pessoa, Integer> {
}
