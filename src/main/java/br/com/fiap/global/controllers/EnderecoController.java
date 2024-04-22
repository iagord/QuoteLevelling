package br.com.fiap.global.controllers;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.global.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.global.repository.EnderecoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/endereco")
public class EnderecoController {
    
        List<Endereco> enderecos = new ArrayList<>();

    @Autowired
    EnderecoRepository repository;

    @GetMapping
    public Page<Endereco> ListAll(@PageableDefault(size = 5) Pageable pageRequest) {
        log.info("buscando todas Enderecos =D!");
        return repository.findAll(pageRequest);
    }

     @GetMapping("{id}")
    public ResponseEntity<Endereco> ListById(@PathVariable Long id) {
        log.info("mostrar Endereco com id " + id);
        return ResponseEntity.ok(getEnderecoById(id));

    }

    @PostMapping
    public ResponseEntity<Object> Insert(@RequestBody @Valid Endereco Endereco) {
        log.info("cadastrando Endereco - " + Endereco);
        repository.save(Endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(Endereco);
    }

    @PutMapping("{id}")
    public ResponseEntity<Endereco> update(@PathVariable Long id, @RequestBody Endereco Endereco){
        log.info("atualizando dados do Endereco com id " + id);
        getEnderecoById(id);
        Endereco.setId_endereco(id);
        repository.save(Endereco);
        return ResponseEntity.ok(Endereco);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> DeleteById(@PathVariable Long id){
        log.info("apagando Endereco com id " + id);
        repository.delete(getEnderecoById(id));
        return ResponseEntity.noContent().build();
    }

    private Endereco getEnderecoById(Long id){
        return repository.findById(id).orElseThrow(() -> { 
             return new RuntimeException();
         });
        }

}
