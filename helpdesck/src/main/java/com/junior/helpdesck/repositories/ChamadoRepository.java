package com.junior.helpdesck.repositories;


import com.junior.helpdesck.domain.Chamado;
import com.junior.helpdesck.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
