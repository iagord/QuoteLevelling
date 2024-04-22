package br.com.fiap.global.controllers;

import br.com.fiap.global.model.Material;
import br.com.fiap.global.repository.MaterialRepository;
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
@RequestMapping("/material")
public class MaterialController {

    List<Material> materiais = new ArrayList<>();

    @Autowired
    MaterialRepository repository;

    @GetMapping
    public Page<Material> ListAll(@PageableDefault(size = 5) Pageable pageRequest) {
        log.info("buscando todas Materiais =D!");
        return repository.findAll(pageRequest);
    }

    @GetMapping("{id}")
    public ResponseEntity<Material> ListById(@PathVariable Long id) {
        log.info("mostrar Material com id " + id);
        return ResponseEntity.ok(getMaterialById(id));

    }

    @PostMapping
    public ResponseEntity<Object> Insert(@RequestBody @Valid Material Material) {
        log.info("cadastrando Material - " + Material);
        repository.save(Material);
        return ResponseEntity.status(HttpStatus.CREATED).body(Material);
    }

    @PutMapping("{id}")
    public ResponseEntity<Material> update(@PathVariable Long id, @RequestBody Material Material){
        log.info("atualizando dados do Material com id " + id);
        getMaterialById(id);
        Material.setId_material(id);
        repository.save(Material);

        return ResponseEntity.ok(Material);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> DeleteById(@PathVariable Long id){
        log.info("apagando Material com id " + id);
        repository.delete(getMaterialById(id));
        return ResponseEntity.noContent().build();
    }

    private Material getMaterialById(Long id){
        return repository.findById(id).orElseThrow(() -> {
            return new RuntimeException();
        });
    }

}
