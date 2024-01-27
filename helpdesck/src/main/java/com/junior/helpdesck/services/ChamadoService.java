package com.junior.helpdesck.services;

import com.junior.helpdesck.domain.Chamado;
import com.junior.helpdesck.domain.Cliente;
import com.junior.helpdesck.domain.Tecnico;
import com.junior.helpdesck.domain.dtos.ChamadoDTO;
import com.junior.helpdesck.domain.enums.Prioridade;
import com.junior.helpdesck.domain.enums.Status;
import com.junior.helpdesck.repositories.ChamadoRepository;
import com.junior.helpdesck.services.exceptions.ObjectnotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;


    @Transactional
    public Chamado findById(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! ID: " + id));
    }


    @Transactional
    public List<Chamado> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Chamado create(@Valid ChamadoDTO objDTO) {
        return repository.save(newChamado(objDTO));


    }

    @Transactional
    public Chamado update(Integer id, ChamadoDTO objDTO) {
        objDTO.setId(id);
        Chamado oldObj = findById(id);
        oldObj = newChamado(objDTO);
        return repository.save(oldObj);
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }


    public Chamado newChamado(ChamadoDTO obj){
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Chamado chamado = new Chamado();
        if(obj.getId() != null){
            chamado.setId(obj.getId());
        }

        if(obj.getStatus().equals(2)){
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacao());
        return chamado;
    }



}
