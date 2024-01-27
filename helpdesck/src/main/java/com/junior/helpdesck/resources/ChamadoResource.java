package com.junior.helpdesck.resources;

import com.junior.helpdesck.domain.Chamado;
import com.junior.helpdesck.domain.dtos.ChamadoDTO;
import com.junior.helpdesck.domain.dtos.ClienteDTO;
import com.junior.helpdesck.services.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chamdos")
public class ChamadoResource {

    @Autowired
    private ChamadoService service;

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
        Chamado obj = service.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        List<Chamado> list = service.findAll();
        List<ChamadoDTO> listDTO = list.stream().map(obj -> new ChamadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> created(@Valid @RequestBody ChamadoDTO objDTO){
        Chamado obj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/{id")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id,
                                             @Valid @RequestBody ChamadoDTO objDTO){
        Chamado newObj = service.update(id,objDTO);
        return ResponseEntity.ok().body(new ChamadoDTO(newObj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ChamadoDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.notFound().build();
    }

}
