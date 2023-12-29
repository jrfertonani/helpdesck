package com.junior.helpdesck.services;

import com.junior.helpdesck.domain.Chamado;
import com.junior.helpdesck.domain.Cliente;
import com.junior.helpdesck.domain.Tecnico;
import com.junior.helpdesck.domain.enums.Perfil;
import com.junior.helpdesck.domain.enums.Prioridade;
import com.junior.helpdesck.domain.enums.Status;
import com.junior.helpdesck.repositories.ChamadoRepository;
import com.junior.helpdesck.repositories.ClienteRepository;
import com.junior.helpdesck.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB(){


        Tecnico tec1 =new Tecnico(null,"Valdir","00000000000","email@email.com","123");
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null,"Linux","11111111111","linux@email.com","123");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO,"Chamado 01","Primeiro Chamado", tec1,cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(c1));

    }

}
