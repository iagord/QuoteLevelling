package br.com.fiap.global.controllers;

import br.com.fiap.global.model.Empresas.Cliente;
import br.com.fiap.global.repository.ClienteRepository;
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
@RequestMapping("/cliente")
public class ClienteController {

    List<Cliente> clientes = new ArrayList<>();

    @Autowired
    ClienteRepository repository;

    @GetMapping
    public Page<Cliente> ListAll(@PageableDefault(size = 5) Pageable pageRequest) {
        log.info("buscando todas Clientes =D!");
        return repository.findAll(pageRequest);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> ListById(@PathVariable Long id) {
        log.info("mostrar Cliente com id " + id);
        return ResponseEntity.ok(getClienteById(id));

    }

    @PostMapping
    public ResponseEntity<Object> Insert(@RequestBody @Valid Cliente Cliente) {
        log.info("cadastrando Cliente - " + Cliente);
        repository.save(Cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(Cliente);
    }

    @PutMapping("{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente Cliente){
        log.info("atualizando dados do Cliente com id " + id);
        getClienteById(id);
        Cliente.setId_empresa(id);
        repository.save(Cliente);

        return ResponseEntity.ok(Cliente);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> DeleteById(@PathVariable Long id){
        log.info("apagando Lembrete com id " + id);
        repository.delete(getClienteById(id));
        return ResponseEntity.noContent().build();
    }

    private Cliente getClienteById(Long id){
        return repository.findById(id).orElseThrow(() -> {
            return new RuntimeException();
        });
    }

}
