package br.com.fiap.global.controllers;

import br.com.fiap.global.model.Responsavel;
import br.com.fiap.global.repository.ResponsavelRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/responsavel")
public class ResponsavelController {

    List<Responsavel> responsaveis = new ArrayList<>();

    @Autowired
    ResponsavelRepository repository;

    @GetMapping
    public Page<Responsavel> ListAll(@PageableDefault(size = 5) Pageable pageRequest) {
        log.info("buscando todas Responsaveis =D!");
        return repository.findAll(pageRequest);
    }

    @GetMapping("{id}")
    public ResponseEntity<Responsavel> ListById(@PathVariable Long id) {
        log.info("mostrar Responsavel com id " + id);
        return ResponseEntity.ok(getResponsavelById(id));

    }

    @PostMapping
    public ResponseEntity<Object> Insert(@RequestBody @Valid Responsavel Responsavel) {
        log.info("cadastrando Responsavel - " + Responsavel);
        repository.save(Responsavel);
        return ResponseEntity.status(HttpStatus.CREATED).body(Responsavel);
    }

    @PutMapping("{id}")
    public ResponseEntity<Responsavel> update(@PathVariable Long id, @RequestBody Responsavel Responsavel){
        log.info("atualizando dados do Responsavel com id " + id);
        getResponsavelById(id);
        Responsavel.setId_responsavel(id);
        repository.save(Responsavel);

        return ResponseEntity.ok(Responsavel);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> DeleteById(@PathVariable Long id){
        log.info("apagando Responsavel com id " + id);
        repository.delete(getResponsavelById(id));
        return ResponseEntity.noContent().build();
    }

    private Responsavel getResponsavelById(Long id){
        return repository.findById(id).orElseThrow(() -> {
            return new RuntimeException();
        });
    }

}
