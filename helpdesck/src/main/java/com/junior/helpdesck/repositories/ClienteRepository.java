package com.junior.helpdesck.repositories;


import com.junior.helpdesck.domain.Cliente;
import com.junior.helpdesck.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
